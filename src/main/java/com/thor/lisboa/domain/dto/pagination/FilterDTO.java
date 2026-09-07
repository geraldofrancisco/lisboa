package com.thor.lisboa.domain.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort.Direction;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class FilterDTO {

  private String cursor;
  private Integer size;

  @Default
  private Direction direction = Direction.DESC;
}
