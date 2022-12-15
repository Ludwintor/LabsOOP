package com.oop.exam;

/**
 * Represents exam result of concrete student
 */
public final class ExamResult {
  /**
   * Student instance
   */
  private final Student student;
  /**
   * Current student attempt result
   */
  private final AttemptResult result;
  /**
   * Total count of attempts
   */
  private final int attempts;

  /**
   * @param student student instance
   * @param result  try result
   */
  public ExamResult(Student student, AttemptResult result, int attempts) {
    this.student = student;
    this.result = result;
    this.attempts = attempts;
  }

  /**
   * Gets student instance
   * @return student instance
   */
  public Student getStudent() {
    return student;
  }

  /**
   * Gets attempt result
   * @return attempt result
   */
  public AttemptResult getResult() {
    return result;
  }

  /**
   * Gets total count of attempts
   * @return total count of attempts
   */
  public int getAttempts() {
    return attempts;
  }

  @Override
  public String toString() {
    return String.format("%s - %s. Attempts: %d", student.getName(), result.toString(), attempts);
  }
}
