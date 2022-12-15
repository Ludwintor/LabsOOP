package com.oop.exam;

import java.util.Random;

/**
 * Represents shared resource
 */
public class Desk {
  /**
   * Delay before making an attempt
   */
  private static final long ATTEMPT_DELAY = 500;
  /**
   * Random instance
   */
  private final Random rng;

  /**
   * Creates desc instance
   */
  public Desk() {
    this.rng = new Random();
  }

  /**
   * Makes attempt to pass an exam and returns attempt result
   * @return attempt result
   */
  public AttemptResult makeAttempt() {
    synchronized (rng) {
      try {
        Thread.sleep(ATTEMPT_DELAY);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int index = rng.nextInt(AttemptResult.class.getEnumConstants().length);
      return AttemptResult.class.getEnumConstants()[index];
    }
  }
}
