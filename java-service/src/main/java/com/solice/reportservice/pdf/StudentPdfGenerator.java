package com.solice.reportservice.pdf;

import com.solice.reportservice.dto.StudentDetailDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

@Component
public class StudentPdfGenerator {

  public byte[] generate(StudentDetailDto s) {
    try (PDDocument doc = new PDDocument()) {
      PDPage page = new PDPage();
      doc.addPage(page);

      try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
        float y = 750;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
        cs.newLineAtOffset(50, y);
        cs.showText("Student Report");
        cs.endText();

        y -= 40;

        y = writeLine(cs, y, "ID: " + safe(s.id));
        y = writeLine(cs, y, "Name: " + safe(s.name));
        y = writeLine(cs, y, "Email: " + safe(s.email));
        y = writeLine(cs, y, "Phone: " + safe(s.phone));
        y = writeLine(cs, y, "Gender: " + safe(s.gender));
        y = writeLine(cs, y, "DOB: " + safe(s.dob));
        y = writeLine(cs, y, "Class: " + safe(s.klass));
        y = writeLine(cs, y, "Section: " + safe(s.section));
        y = writeLine(cs, y, "Roll: " + safe(s.roll));
        y = writeLine(cs, y, "Admission Date: " + safe(s.admissionDate));
        y = writeLine(cs, y, "System Access: " + safe(s.systemAccess));

        y -= 10;
        y = writeLine(cs, y, "Father: " + safe(s.fatherName) + " (" + safe(s.fatherPhone) + ")");
        y = writeLine(cs, y, "Mother: " + safe(s.motherName) + " (" + safe(s.motherPhone) + ")");
        y =
            writeLine(
                cs, y, "Guardian: " + safe(s.guardianName) + " (" + safe(s.guardianPhone) + ")");
        y = writeLine(cs, y, "Relation: " + safe(s.relationOfGuardian));

        y -= 10;
        y = writeLine(cs, y, "Current Address: " + safe(s.currentAddress));
        y = writeLine(cs, y, "Permanent Address: " + safe(s.permanentAddress));
        y = writeLine(cs, y, "Reporter: " + safe(s.reporterName));
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      doc.save(baos);
      return baos.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException("Failed to generate PDF", e);
    }
  }

  private float writeLine(PDPageContentStream cs, float y, String text) throws IOException {
    cs.beginText();
    cs.setFont(PDType1Font.HELVETICA, 12);
    cs.newLineAtOffset(50, y);
    cs.showText(text);
    cs.endText();
    return y - 18;
  }

  private String safe(Object o) {
    return o == null ? "-" : String.valueOf(o);
  }
}
