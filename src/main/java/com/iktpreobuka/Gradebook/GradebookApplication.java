package com.iktpreobuka.Gradebook;

import com.iktpreobuka.Gradebook.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class GradebookApplication {
//    private static final Logger logger = LoggerFactory.getLogger(GradebookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GradebookApplication.class, args);
    }

}
