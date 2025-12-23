package com.solice.reportservice;

import com.solice.reportservice.config.BackendProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BackendProperties.class)
public class JavaServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaServiceApplication.class, args);
  }
}
