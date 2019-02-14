package com.rhod.kalah.services.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.rhod.kalah.repository.RepositoryConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@Import(RepositoryConfiguration.class)
public class RepositoryServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args  Program arguments
	 */
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "repository-server");

        SpringApplication.run(RepositoryServer.class, args);
    }
}