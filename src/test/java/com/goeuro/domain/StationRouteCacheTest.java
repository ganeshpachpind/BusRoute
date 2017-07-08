package com.goeuro.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class StationRouteCacheTest {


    @Test
    public void shouldAddNewStationWhenStationEntryDoesNotExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);

        Map<Integer, ArrayList<Integer>> stationRoutes = stationRouteCache.stationRoutes;
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRouteCache.stationRoutes.get(1).size(), is(1));

    }

    @Test
    public void shouldUpdateStationWhenStationEntryExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);
        stationRouteCache.update(1, 11);

        Map<Integer, ArrayList<Integer>> stationRoutes = stationRouteCache.stationRoutes;
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRouteCache.stationRoutes.get(1).size(), is(2));
    }

}