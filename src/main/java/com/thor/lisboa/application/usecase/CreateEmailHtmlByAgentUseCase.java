package com.thor.lisboa.application.usecase;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationQuestRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateEmailHtmlByAgentUseCase extends BaseUseCase<Pair<String, String>, String>{

  private final AgentIntegration agentIntegration;

  private static final String GENERATE_EMAIL_HTML_PROMPT = """
      Tenho o seguinte texto gerado a partir de um arquivo excel:
      %s
      Tenho o seguinte template de email, substitua as variáves com interpolação utilizando o texto do excel, e gere o html do email:
      %s
      Gere o html do email, não gere o texto do email e nem explicações ou contextos, garanta que tenha apenas o html.
      """;

  @Override
  public String execute(Pair<String, String> pair) {
    var prompt = GENERATE_EMAIL_HTML_PROMPT.formatted(pair.getFirst(), pair.getSecond());
    var request = AgentIntegrationQuestRequest.builder()
        .question(prompt)
        .build();
    return agentIntegration.question(request).getData();
  }
}
