package br.jh.lembre.me;

import org.springframework.boot.SpringApplication;
import br.jh.lembre.me.config.WebConfig;

public class Run {
	
	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}
	
}