package com.goeuro;

import com.goeuro.service.BusRouteCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BusRouteApplication implements CommandLineRunner {

    @Autowired
    BusRouteCacheService busRouteCacheService;

    @Value("#{'${routes-file-path:data/example}'}")
    private String routesFilePath;

    public static void main(String[] args) {
        SpringApplication.run(BusRouteApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        busRouteCacheService.buildStationRouteCache(routesFilePath);
    }
}
