package com.queined.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inarixdono.literalura.main.Main;
import com.inarixdono.literalura.service.LiteraluraService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	@Autowired
	private LiteraluraService service;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(service);
		main.main();
	}

}