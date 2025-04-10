package org.sopt.util;

public class PostIdGenerator {

  private static int id = 1;

  public static int generateId() {
    return id++;
  }

  public static void initializedId(final int startId) {
    id = startId;
  }
}
