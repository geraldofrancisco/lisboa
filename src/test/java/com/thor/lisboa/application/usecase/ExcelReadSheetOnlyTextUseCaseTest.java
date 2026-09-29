package com.thor.lisboa.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class ExcelReadSheetOnlyTextUseCaseTest {

  private final ExcelReadSheetOnlyTextUseCase useCase = new ExcelReadSheetOnlyTextUseCase();

  @Test
  void readsTheFirstSheetAsTabSeparatedText() throws Exception {
    byte[] workbookBytes;
    try (var workbook = new XSSFWorkbook(); var output = new ByteArrayOutputStream()) {
      var sheet = workbook.createSheet("emails");
      sheet.createRow(0).createCell(0).setCellValue("name");
      sheet.getRow(0).createCell(1).setCellValue("email");
      sheet.createRow(1).createCell(0).setCellValue("Ada");
      sheet.getRow(1).createCell(1).setCellValue("ada@example.com");
      workbook.write(output);
      workbookBytes = output.toByteArray();
    }

    var file = new MockMultipartFile("file", "email.xlsx", "application/octet-stream", workbookBytes);

    assertEquals("name\temail\nAda\tada@example.com", useCase.execute(file));
  }

  @Test
  void returnsEmptyTextWhenWorkbookCannotBeRead() throws Exception {
    MultipartFile file = mock(MultipartFile.class);
    when(file.getInputStream()).thenThrow(new java.io.IOException("unavailable"));

    assertEquals("", useCase.execute(file));
  }
}
