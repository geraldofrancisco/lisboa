package com.thor.lisboa.domain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class JsonUtilTest {

  @Test
  void fromJsonConvertsValidJsonToRequestedType() {
    var result = JsonUtil.fromJson("{\"name\":\"Lisboa\",\"count\":2}", SampleData.class);

    assertEquals(new SampleData("Lisboa", 2), result);
  }

  @Test
  void fromJsonReturnsNullForInvalidJson() {
    assertNull(JsonUtil.fromJson("{\"name\":}", SampleData.class));
  }

  @Test
  void fromJsonReturnsNullForEmptyInput() {
    assertNull(JsonUtil.fromJson("", SampleData.class));
  }

  @Test
  void fromJsonReturnsNullForJsonWithTrailingContent() {
    assertNull(JsonUtil.fromJson("{\"name\":\"Lisboa\",\"count\":2} trailing", SampleData.class));
  }

  @Test
  void fromJsonReturnsNullWhenInputIsNull() {
    assertNull(JsonUtil.fromJson(null, SampleData.class));
  }

  @Test
  void toJsonSerializesObject() throws JsonProcessingException {
    var result = JsonUtil.toJson(new SampleData("Lisboa", 2));

    assertEquals("{\"name\":\"Lisboa\",\"count\":2}", result);
  }

  @Test
  void toJsonPropagatesSerializationFailure() {
    assertThrows(JsonProcessingException.class, () -> JsonUtil.toJson(new FailingData()));
  }

  record SampleData(String name, int count) {
  }

  @JsonSerialize(using = FailingDataSerializer.class)
  static class FailingData {
  }

  static class FailingDataSerializer extends JsonSerializer<FailingData> {

    @Override
    public void serialize(FailingData value, com.fasterxml.jackson.core.JsonGenerator generator,
        SerializerProvider serializers) throws IOException {
      throw new JsonProcessingException("serialization failure") {
      };
    }
  }
}
