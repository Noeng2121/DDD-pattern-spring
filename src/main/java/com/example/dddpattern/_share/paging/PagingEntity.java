package com.example.dddpattern._share.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Data
public class PagingEntity {

  public static final String SIZE = "size";
  public static final String PAGE = "page";
  public static final String TOTAL = "total";
  public static final String TOTAL_PAGES = "totalPages";

  private int size;
  private int page;
  private int total;
  private int totalPages;

  public static PagingEntity from(Map<String, Object> parameters) {

    if (Optional.ofNullable(parameters).map(Map::isEmpty).orElse(Boolean.TRUE)) {
      return null;
    }

    PagingEntity paging = new PagingEntity();

    paging.setSize((Integer) parameters.getOrDefault(PagingEntity.SIZE, 0));
    paging.setTotal((Integer) parameters.getOrDefault(PagingEntity.TOTAL, 0));
    paging.setPage((Integer) parameters.getOrDefault(PagingEntity.PAGE, 0));
    paging.setTotalPages((Integer) parameters.getOrDefault(PagingEntity.TOTAL_PAGES, 0));

    return paging;
  }
}
