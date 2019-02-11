package com.rhod.kalah.services.logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.rhod.kalah.logic.LogicConfiguration;

/**
 * Logic Server used to control game logic
 * @author Rhodri
 *
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@Import(LogicConfiguration.class)
public class LogicServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args  Program arguments
	 */
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "logic-server");

        SpringApplication.run(LogicServer.class, args);
    }
}