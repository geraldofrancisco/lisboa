package com.thor.lisboa.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

class UseCaseTest {

  @Test
  void baseUseCaseDelegatesToItsDefaultImplementation() {
    BaseUseCase<String, String> useCase = new BaseUseCase<>() {};

    assertNull(useCase.execute("input"));
  }

  @Test
  void emailHtmlUseCaseReturnsTheSpreadsheetText() {
    var useCase = new CreateEmailHtmlByAgentUseCase(mock(AgentIntegration.class));

    assertEquals("spreadsheet", useCase.execute(Pair.of("spreadsheet", "template")));
  }
}
