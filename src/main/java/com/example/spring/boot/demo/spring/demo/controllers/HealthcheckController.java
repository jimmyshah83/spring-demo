package com.example.spring.boot.demo.spring.demo.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Backbase - Codility challenge (30/07/2019)
 * Check-in date -> (31/07/2019)
 * Code changes made after re-visiting the test to make it more readable 
 * @author jishah
 *
 */
@SuppressWarnings({"rawtypes", "unused"})
@RestController
public class HealthcheckController {
	
	DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

	@GetMapping(value = "/healthcheck")
	public ResponseEntity<?> healthcheck(@RequestParam String format) {		
		if (Format.SHORT.getValue().equals(format)) 
			return new ResponseEntity<HealthcheckController.Health>(new HealthCheckShort(), HttpStatus.OK);
		else if (Format.FULL.getValue().equals(format))
			return new ResponseEntity<HealthcheckController.Health>(new HealthCheckFull(), HttpStatus.OK);
		else 
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/healthcheck")
	public ResponseEntity healthcheckPut() {
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PostMapping(value = "/healthcheck")
	public ResponseEntity healthcheckPost() {
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@DeleteMapping(value = "/healthcheck")
	public ResponseEntity healthcheckDelete() {
		return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	private enum Format {
		SHORT("short"),
		FULL("full"),;
		private String value;
		Format(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	private interface Health {}

	private class HealthCheckFull implements Health {
		private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		private TimeZone tz = TimeZone.getTimeZone("UTC");
		
		public HealthCheckFull() {
			this.application = "OK";
			df.setTimeZone(tz);
		}
		private LocalDateTime currentTime;
		private String application;
		public String getApplication() {
			return application;
		}
		public String getCurrentTime() {
			return df.format(new Date());
		}
	}

	private class HealthCheckShort implements Health {
		public HealthCheckShort() {
			this.status = "OK";
		}
		private String status;
		public String getStatus() {
			return status;
		}
	}
}
