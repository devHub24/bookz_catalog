package com.sk.bookz_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookzCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookzCatalogApplication.class, args);
	}

}
