package com.thor.lisboa.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

class UseCaseTest {

  @Test
  void baseUseCaseDelegatesToItsDefaultImplementation() {
    BaseUseCase<String, String> useCase = new BaseUseCase<>() {};

    assertNull(useCase.execute("input"));
  }

  @Test
  void emailHtmlUseCaseReturnsTheGeneratedHtml() {
    var agentIntegration = mock(AgentIntegration.class);
    when(agentIntegration.question(org.mockito.ArgumentMatchers.any()))
        .thenReturn(AgentIntegrationResponseDTO.builder().data("<html>email</html>").build());
    var useCase = new CreateEmailHtmlByAgentUseCase(agentIntegration);

    assertEquals("<html>email</html>", useCase.execute(Pair.of("spreadsheet", "template")));
  }
}
