package com.solice.reportservice.client;

import com.solice.reportservice.config.BackendProperties;
import com.solice.reportservice.dto.StudentDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class StudentApiClient {

  private final BackendProperties props;

  public StudentDetailResponse fetchStudentDetail(long studentId) {
    RestClient client =
        RestClient.builder()
            .baseUrl(props.baseUrl())
            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
            .defaultHeader("X-API-Key", props.apiKey())
            .build();

    return client
        .get()
        .uri(props.studentDetailPath(), studentId)
        .retrieve()
        .body(StudentDetailResponse.class);
  }
}
