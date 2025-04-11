package com.example.dddpattern._share.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paging<T> {

  private List<T> items;
  private int size;
  private int page;
  private int total;
  private int totalPages;
}
