package ua.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.server.config.DBConfig;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] { App.class, DBConfig.class }, args);
	}
}
