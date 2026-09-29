package com.thor.lisboa;

import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

class ApplicationTests {

  @Test
  void mainStartsTheApplication() {
    String[] args = {"--spring.main.web-application-type=none"};
    new Application();

    try (var springApplication = mockStatic(SpringApplication.class)) {
      Application.main(args);

      springApplication.verify(() -> SpringApplication.run(Application.class, args));
    }
  }
}
