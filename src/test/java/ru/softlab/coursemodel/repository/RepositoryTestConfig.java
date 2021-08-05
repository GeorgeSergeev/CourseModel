package ru.softlab.coursemodel.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = "ru.softlab.coursemodel.repository")
@EntityScan(basePackages = "ru.softlab.coursemodel.model")
public class RepositoryTestConfig {
}
