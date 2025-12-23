package com.solice.reportservice.dto;

import lombok.Data;

@Data
public class StudentDetailDto {
  public Integer id;
  public String name;
  public String email;
  public Boolean systemAccess;

  public String phone;
  public String gender;
  public String dob;

  public String klass;
  public String section;
  public Integer roll;

  public String fatherName;
  public String fatherPhone;
  public String motherName;
  public String motherPhone;

  public String guardianName;
  public String guardianPhone;
  public String relationOfGuardian;

  public String currentAddress;
  public String permanentAddress;

  public String admissionDate;
  public String reporterName;
}
