package com.goeuro;

import com.goeuro.domain.StationRouteCache;
import com.goeuro.util.StationRouteCacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BusroutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusroutesApplication.class, args);
	}
}
