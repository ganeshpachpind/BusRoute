package com.goeuro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeuro.model.DirectBusRouteResponse;
import com.goeuro.service.BusRouteCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusRouteControllerTest {
    private static final int DEPARTURE_STATION_ID = 1000;
    private static final int ARRIVAL_STATION_ID = 5000;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusRouteCacheService busRouteCacheService;

    @Test
    public void shouldGetDirectBusRouteResponse() throws Exception {
        when(busRouteCacheService.isDirectRouteExist(DEPARTURE_STATION_ID, ARRIVAL_STATION_ID)).thenReturn(true);
        String url = String.format("/api/direct?dep_sid=%s&arr_sid=%s", DEPARTURE_STATION_ID, ARRIVAL_STATION_ID);
        ObjectMapper objectMapper = new ObjectMapper();
        DirectBusRouteResponse directBusRouteResponse = new DirectBusRouteResponse(DEPARTURE_STATION_ID, ARRIVAL_STATION_ID, true);


        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(directBusRouteResponse), true))
                .andReturn();

    }

    @Test
    public void shouldReturnBadRequestWhenArrivalStationIdIsMissing() throws Exception {
        String url = String.format("/api/direct?arr_sid=%s", ARRIVAL_STATION_ID);

        this.mockMvc.perform(get(url)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenDepartureStationIdIsMissing() throws Exception {
        String url = String.format("/api/direct?dep_sid=%s", DEPARTURE_STATION_ID);

        this.mockMvc.perform(get(url)).andExpect(status().isBadRequest());
    }
}