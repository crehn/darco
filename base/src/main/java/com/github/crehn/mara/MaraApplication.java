package com.github.crehn.mara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@SpringBootApplication
public class MaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaraApplication.class, args);
	}

}
