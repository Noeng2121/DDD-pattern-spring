package com.example.dddpattern._share.paging;

public class PageNumberUtility {
  public static int in(int page) {
    return page - 1;
  }

  public static int out(int page) {
    return page + 1;
  }
}
