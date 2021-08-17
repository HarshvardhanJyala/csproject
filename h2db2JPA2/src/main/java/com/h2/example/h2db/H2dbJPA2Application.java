package com.h2.example.h2db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.h2.example.h2db.repository.EventJPARepository;
import com.h2.example.h2db.service.JsonArrayOperations;

@SpringBootApplication
public class H2dbJPA2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EventJPARepository repository;

	@Autowired
	JsonArrayOperations jaop;

	public static void main(String[] args) {
		SpringApplication.run(H2dbJPA2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			Path p = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\fileFolder\\logFile.txt.txt");

			List<String> completeLogList = Files.readAllLines(p);

			for (String line : completeLogList) {
				jaop.createJSONArrayUsingPOJO(line);
			}
			jaop.parseArrayList();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
