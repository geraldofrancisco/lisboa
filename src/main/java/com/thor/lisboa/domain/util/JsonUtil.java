package com.thor.lisboa.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .findAndRegisterModules()
      .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);

  public static <T> T fromJson(String json, Class<T> targetClass) {
    if (json == null) {
      return null;
    }

    JsonNode jsonNode;
    try {
      jsonNode = OBJECT_MAPPER.readTree(json);
    } catch (JsonProcessingException exception) {
      return null;
    }

    return OBJECT_MAPPER.convertValue(jsonNode, targetClass);
  }

  public static String toJson(Object value) throws JsonProcessingException {
    return OBJECT_MAPPER.writeValueAsString(value);
  }
}
