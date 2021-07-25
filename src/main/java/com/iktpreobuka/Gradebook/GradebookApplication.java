package com.iktpreobuka.Gradebook;

import com.iktpreobuka.Gradebook.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class GradebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradebookApplication.class, args);
	}

}
