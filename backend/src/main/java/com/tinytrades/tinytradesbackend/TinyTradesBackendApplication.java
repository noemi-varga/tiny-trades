package com.tinytrades.tinytradesbackend;

import com.tinytrades.tinytradesbackend.security.service.DataInitService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TinyTradesBackendApplication {

	private final DataInitService dataInitService;

	@Autowired
	public TinyTradesBackendApplication(DataInitService dataInitService) {
		this.dataInitService = dataInitService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TinyTradesBackendApplication.class, args);
	}

//	@PostConstruct
//	public void seedDatabase() {
//		dataInitService.seedDatabase();
//	}

}
