package com.thor.lisboa.application.usecase;

import static com.thor.lisboa.domain.constants.ExcelConstants.EXCEL__ERROR_READING;

import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class ExcelReadSheetOnlyTextUseCase {

  public String read(MultipartFile file) {
    return read(file, 0);
  }

  public String read(MultipartFile file, Integer sheetNumber) {
    try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
      Sheet sheet = workbook.getSheetAt(sheetNumber);
      DataFormatter formatter = new DataFormatter();
      return StreamSupport.stream(sheet.spliterator(), false)
          .map(row -> StreamSupport.stream(row.spliterator(), false)
              .map(formatter::formatCellValue)
              .collect(Collectors.joining("\t"))
          )
          .collect(Collectors.joining("\n"));
    } catch (Exception e) {
      log.error(EXCEL__ERROR_READING, e);
      return StringUtils.EMPTY;
    }
  }
}
