package com.thor.lisboa.domain.response.email;


import com.thor.lisboa.domain.response.PageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmailPageResponse extends PageResponse<EmailResponse> {

}
