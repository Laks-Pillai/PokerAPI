package com.poker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * This application is created for showcasing springboot for creating a rest based API for 
 * a basic poker game
 * 
 * PokerCardGameApp - this is the bootstrap class. It starts the application on a Spring container. 
 * 
 * */
@SpringBootApplication
public class PokerCardGameApp {
	public static void main(String[] args) {
		SpringApplication.run(PokerCardGameApp.class, args);
	}

}
