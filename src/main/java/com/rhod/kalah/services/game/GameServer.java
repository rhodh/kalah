package com.rhod.kalah.services.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.rhod.kalah.game.GameController;
import com.rhod.kalah.game.GameOrchestrater;

/**
 * Game Server used to control manage the player interaction
 * @author Rhodri
 *
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class GameServer {
	
	/**
	 * URL uses the logical name of logic-service
	 */
	private static final String LOGIC_SERVICE_URL = "http://LOGIC-SERVICE/";
	
	/**
	 * URL uses the logical name of repository-service
	 */
	private static final String REPOSITORY_SERVICE_URL = "http://REPOSITORY-SERVICE/";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args  Program arguments
	 */
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "game-server");

        SpringApplication.run(GameServer.class, args);
    }
    
	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * The GameOrchestrater that orchestrates the mircoservices 
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public GameOrchestrater gameOrchestrater() {
		return new GameOrchestrater(REPOSITORY_SERVICE_URL, LOGIC_SERVICE_URL);
	}
	
	/**
	 * Create the controller, passing it the {@link GameOrchestrater} to use.
	 * 
	 * @return
	 */
	@Bean
	public GameController accountsController() {
		return new GameController(gameOrchestrater());
	}

}