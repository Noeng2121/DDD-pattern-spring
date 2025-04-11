package com.example.dddpattern._share.paging;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponseEntity<T> {

  @Builder.Default private Integer status = HttpStatus.OK.value();

  @JsonInclude @Builder.Default private String message = "Successful";

  @JsonInclude private T data;

  private PagingEntity paging;

  private ErrorEntity error;

  public HttpResponseEntity(HttpStatus status, String message, T data) {
    this.status = status.value();
    this.message = message;
    this.data = data;
  }

  public HttpResponseEntity(HttpStatus status, String message, ErrorEntity error) {
    this.status = status.value();
    this.message = message;
    this.error = error;
  }

  public HttpResponseEntity(HttpStatus status, String message, List<T> data) {
    this.status = status.value();
    this.message = message;
    this.data = (T) data;
  }

  public HttpResponseEntity(HttpStatus status, String message, List<T> data, Object paging) {
    this.status = status.value();
    this.message = message;
    this.data = (T) data;
    this.paging =
        paging instanceof Map
            ? PagingEntity.from((Map<String, Object>) paging)
            : PagingEntity.from(parameters(paging));
  }

  public void setStatus(HttpStatus status) {
    this.status = status.value();
  }

  public Map<String, Object> parameters(Object obj) {
    if (obj == null) {
      return null;
    }
    Map<String, Object> map = new HashMap<>();
    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(obj));
      } catch (Exception e) {
      }
    }
    return map;
  }
}
