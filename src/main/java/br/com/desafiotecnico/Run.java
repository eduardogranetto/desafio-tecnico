package br.com.desafiotecnico;

import org.springframework.boot.SpringApplication;

import br.com.desafiotecnico.config.WebConfig;

public class Run {
	
	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}
	
}