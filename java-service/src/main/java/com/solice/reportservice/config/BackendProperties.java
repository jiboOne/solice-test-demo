package com.solice.reportservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "backend")
public record BackendProperties(String baseUrl, String studentDetailPath, String apiKey) {}
