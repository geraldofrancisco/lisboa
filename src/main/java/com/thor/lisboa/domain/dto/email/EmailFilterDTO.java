package com.thor.lisboa.domain.dto.email;

import com.thor.lisboa.domain.dto.pagination.FilterDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmailFilterDTO extends FilterDTO {

  private LocalDateTime startCreatedDate;
  private LocalDateTime endCreatedDate;
  private ObjectId emailTypeId;
  private LocalDateTime startSendDate;
  private LocalDateTime endSendDate;
}
