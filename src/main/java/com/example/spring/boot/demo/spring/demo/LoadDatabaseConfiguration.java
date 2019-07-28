package com.example.spring.boot.demo.spring.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring.boot.demo.spring.demo.models.Bond;
import com.example.spring.boot.demo.spring.demo.persistence.BondRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabaseConfiguration {

    @Bean
    public CommandLineRunner initializeDatabase(BondRepository bondRepository) {
	return args -> {
	    log.info("Loading: " + bondRepository.save(new Bond("American Honda Fin. 19/22", "XS1957532887", 150d, 101.22d)));
	    log.info("Loading: " + bondRepository.save(new Bond("Toyota Finance Australia 04/21", "XS1978200555", 250d, 106.76d)));
	    log.info("Loading: " + bondRepository.save(new Bond("BMW 07/26", "XS2010447238", 50d, 98.176d)));
	};
    }
}
