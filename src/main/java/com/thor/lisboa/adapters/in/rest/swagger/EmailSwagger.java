package com.thor.lisboa.adapters.in.rest.swagger;

import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_EMAIL_TYPE_ID_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_END_CREATED_DATE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_END_SEND_DATE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_RESPONSE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_START_CREATED_DATE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_START_SEND_DATE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_GET_BY_FILTER_SUMMARY;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_TAG_DESCRIPTION;
import static com.thor.lisboa.domain.constants.EmailConstants.EMAIL_CONTROLLER_TAG_NAME;
import static com.thor.lisboa.domain.constants.ProjectConstants.DEFAULT_PAGE_SIZE_VALUE;
import static com.thor.lisboa.domain.constants.ProjectConstants.HEADER_GET_BY_FILTER_CURSOR_DESCRIPTION;
import static com.thor.lisboa.domain.constants.ProjectConstants.PROJECT_SWAGGER_STATUS_OK;
import static com.thor.lisboa.domain.constants.ProjectConstants.QUERY_GET_BY_FILTER_SIZE_DESCRIPTION;
import static com.thor.lisboa.domain.constants.ProjectConstants.QUERY_GET_BY_FILTER__DIRECTION_DESCRIPTION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.thor.lisboa.domain.request.validation.ValueOfEnum;
import com.thor.lisboa.domain.response.email.EmailPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = EMAIL_CONTROLLER_TAG_NAME, description = EMAIL_CONTROLLER_TAG_DESCRIPTION)
public interface EmailSwagger {

  String teste(@RequestParam("file") MultipartFile file);
  @Operation(
      summary = EMAIL_CONTROLLER_GET_BY_FILTER_SUMMARY,
      description = EMAIL_CONTROLLER_GET_BY_FILTER_DESCRIPTION,
      responses =
      @ApiResponse(
          responseCode = PROJECT_SWAGGER_STATUS_OK,
          description = EMAIL_CONTROLLER_GET_BY_FILTER_RESPONSE_DESCRIPTION,
          content =
          @Content(
              mediaType = APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = EmailPageResponse.class))))
  EmailPageResponse getByFilter(
      @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE_VALUE)
      @Parameter(description = QUERY_GET_BY_FILTER_SIZE_DESCRIPTION)
      Integer size,

      @RequestParam(required = false)
      @Parameter(description = EMAIL_CONTROLLER_GET_BY_FILTER_START_CREATED_DATE_DESCRIPTION)
      String startCreatedDate,

      @RequestParam(required = false)
      @Parameter(description = EMAIL_CONTROLLER_GET_BY_FILTER_END_CREATED_DATE_DESCRIPTION)
      String endCreatedDate,

      @RequestParam(required = false)
      @Parameter(description = EMAIL_CONTROLLER_GET_BY_FILTER_EMAIL_TYPE_ID_DESCRIPTION)
      String emailTypeId,

      @RequestParam(required = false)
      @Parameter(description = EMAIL_CONTROLLER_GET_BY_FILTER_START_SEND_DATE_DESCRIPTION)
      String startSendDate,

      @RequestParam(required = false)
      @Parameter(description = EMAIL_CONTROLLER_GET_BY_FILTER_END_SEND_DATE_DESCRIPTION)
      String endSendDate,

      @RequestParam(required = false, defaultValue = "DESC")
      @Parameter(description = QUERY_GET_BY_FILTER__DIRECTION_DESCRIPTION)
      @ValueOfEnum(enumClass = Direction.class)
      String direction,

      @RequestHeader(required = false) @Parameter(description = HEADER_GET_BY_FILTER_CURSOR_DESCRIPTION)
      String cursor
  );
}
