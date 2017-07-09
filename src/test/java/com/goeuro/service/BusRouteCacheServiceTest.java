package com.goeuro.service;

import com.goeuro.model.BusRouteCache;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BusRouteCacheServiceTest {

    private BusRouteCacheService busRouteCacheService;

    @Before
    public void setUp() throws Exception {
        busRouteCacheService = new BusRouteCacheService(new BusRouteCache());
    }

    @Test(expected = IOException.class)
    public void shouldThrowIoExceptionWhenFileNotFound() throws Exception {
        busRouteCacheService.buildStationRouteCache("some dummy file path");
    }

    @Test
    public void shouldCreateBusRouteCacheFromAGivenFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String routesFilePath = classLoader.getResource("routes").getPath();

        BusRouteCache busRouteCache = busRouteCacheService.buildStationRouteCache(routesFilePath);

        Map<Integer, ArrayList<Integer>> stationRoutes = busRouteCache.getStationRoutes();
        assertThat(stationRoutes.size(), is(5));
        assertThat(stationRoutes.get(2).size(), is(1));
        assertThat(stationRoutes.get(1).size(), is(2));
        assertThat(stationRoutes.get(1), is(Arrays.asList(0, 1)));
    }
}