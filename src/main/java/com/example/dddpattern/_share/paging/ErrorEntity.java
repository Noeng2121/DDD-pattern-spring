package com.example.dddpattern._share.paging;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorEntity {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String type;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String code;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String error;
}
