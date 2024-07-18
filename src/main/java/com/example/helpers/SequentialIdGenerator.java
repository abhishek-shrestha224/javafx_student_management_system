package com.example.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SequentialIdGenerator {
  private static final Map<String, AtomicInteger> sequenceMap = new HashMap<>();

  public static String generateId(String prefix) {
    sequenceMap.putIfAbsent(prefix, new AtomicInteger(1));

    int seq = sequenceMap.get(prefix).getAndIncrement();
    String formattedSeq = String.format("%05d", seq);

    return prefix + "-" + formattedSeq;
  }

}
