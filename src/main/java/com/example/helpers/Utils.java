package com.example.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {

  private static final Map<String, AtomicInteger> sequenceMap = new HashMap<>();

  public static String generateId(String prefix) {
    sequenceMap.putIfAbsent(prefix, new AtomicInteger(1));

    int seq = sequenceMap.get(prefix).getAndIncrement();
    String formattedSeq = String.format("%05d", seq);

    return prefix + "-" + formattedSeq;
  }

  public static String getSha256Hash(String input) {
    return DigestUtils.sha256Hex(input);
  }

}
