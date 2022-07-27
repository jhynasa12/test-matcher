package com.applause.test.matcher.testmatcher.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utility {

  public static <T> Collector<T, ?, T> toSingleton() {
    return Collectors.collectingAndThen(
        Collectors.toList(),
        list -> {
          if (list.isEmpty()) {
            return null;
          }
          if (list.size() != 1) {
            throw new IllegalStateException();
          }
          return list.get(0);
        });
  }
}
