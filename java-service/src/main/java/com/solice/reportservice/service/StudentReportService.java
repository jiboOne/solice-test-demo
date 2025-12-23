package com.solice.reportservice.service;

import com.solice.reportservice.client.StudentApiClient;
import com.solice.reportservice.pdf.StudentPdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentReportService {

  private final StudentApiClient apiClient;
  private final StudentPdfGenerator pdfGenerator;

  public byte[] generateStudentReportPdf(long studentId) {
    var response = apiClient.fetchStudentDetail(studentId);

    if (response == null || response.getData() == null) {
      return null;
    }

    return pdfGenerator.generate(response.getData());
  }
}
