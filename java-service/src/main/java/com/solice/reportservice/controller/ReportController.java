package com.solice.reportservice.controller;

import com.solice.reportservice.service.StudentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

  private final StudentReportService studentReportService;

  @GetMapping("/students/{id}.pdf")
  public ResponseEntity<byte[]> studentReport(@PathVariable long id) {
    byte[] pdf = studentReportService.generateStudentReportPdf(id);

    if (pdf == null) {
      return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDisposition(
        ContentDisposition.attachment().filename("student-" + id + "-report.pdf").build());

    return ResponseEntity.ok().headers(headers).body(pdf);
  }
}
