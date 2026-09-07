package com.thor.lisboa.application.service;

import com.thor.lisboa.application.usecase.ExcelReadSheetOnlyTextUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final ExcelReadSheetOnlyTextUseCase excelRead;

  public String teste(MultipartFile file) {
    return excelRead.read(file);
  }
}
