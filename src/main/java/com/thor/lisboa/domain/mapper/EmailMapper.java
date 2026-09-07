package com.thor.lisboa.domain.mapper;


import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.util.DateTimeUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort.Direction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailMapper extends PageMapper {

  public static EmailFilterDTO toFilter(
      String startCreatedDate, String endCreatedDate, String emailTypeId, String startSendDate,
      String endSendDate, String cursor, Integer size, String direction
  ) {
    var builder = EmailFilterDTO.builder()
        .cursor(cursor)
        .direction(Direction.valueOf(direction))
        .size(size);

    if (StringUtils.isNotBlank(emailTypeId) && ObjectId.isValid(emailTypeId)) {
      builder.emailTypeId(new ObjectId(emailTypeId));
    }

    if (DateTimeUtil.validateDateTime(startCreatedDate)) {
      builder.startCreatedDate(DateTimeUtil.toLocalDateTime(startCreatedDate));
    }

    if (DateTimeUtil.validateDateTime(endCreatedDate)) {
      builder.endCreatedDate(DateTimeUtil.toLocalDateTime(endCreatedDate));
    }

    if (DateTimeUtil.validateDateTime(startSendDate)) {
      builder.startSendDate(DateTimeUtil.toLocalDateTime(startSendDate));
    }

    if (DateTimeUtil.validateDateTime(endSendDate)) {
      builder.endSendDate(DateTimeUtil.toLocalDateTime(endSendDate));
    }

    return builder.build();
  }

}
