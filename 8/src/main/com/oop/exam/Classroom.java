package com.oop.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Represents classroom for exam
 */
public class Classroom {
  /**
   * Shared desk instance
   */
  private final Desk desk;

  /**
   * Creates classroom instance
   */
  public Classroom() {
    desk = new Desk();
  }

  /**
   * Takes an exam for provided count of students and returns result when everyone finished
   * @param studentsCount students count
   * @return exam results of each student
   */
  public List<ExamResult> takeExam(int studentsCount) {
    Student[] students = new Student[studentsCount];
    for (int i = 0; i < studentsCount; i++)
      students[i] = new Student(String.format("Student %d", i + 1), desk);
    ExecutorService executor = Executors.newFixedThreadPool(studentsCount);
    List<Future<ExamResult>> tasks;
    try {
      tasks = executor.invokeAll(Arrays.asList(students));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    List<ExamResult> results = new ArrayList<>(tasks.size());
    for (Future<ExamResult> task : tasks) {
      ExamResult examResult;
      try {
        examResult = task.get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
      results.add(examResult);
    }
    executor.shutdown();
    return results;
  }
}
