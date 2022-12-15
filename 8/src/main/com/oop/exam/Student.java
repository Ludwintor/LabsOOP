package com.oop.exam;

import java.util.concurrent.Callable;

/**
 * Represents "thread" as student
 */
public class Student implements Callable<ExamResult> {
  private static final long STUDENT_DELAY = 800;
  /**
   * Student's name
   */
  private final String name;
  /**
   * Shared desk
   */
  private final Desk desk;

  /**
   * Creates student instance
   * @param name student's name
   * @param desk shared desk
   */
  public Student(String name, Desk desk) {
    this.name = name;
    this.desk = desk;
  }

  /**
   * Gets student name
   * @return student's name
   */
  public String getName() {
    return name;
  }

  @Override
  public ExamResult call() {
    int attemptsCount = 0;
    AttemptResult result;
    do {
      attemptsCount++;
      try {
        Thread.sleep(STUDENT_DELAY);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      result = desk.makeAttempt();
      System.out.format("%s made an attempt with result %s. Attempts: %d\n", name, result.toString(), attemptsCount);
    } while (result == AttemptResult.RETRY);
    return new ExamResult(this, result, attemptsCount);
  }
}
