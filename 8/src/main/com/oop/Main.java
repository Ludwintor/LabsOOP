package com.oop;

import com.oop.exam.Classroom;
import com.oop.exam.ExamResult;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Entry point class
 */
public class Main {
  /**
   * Entry point
   */
  public static void main(String[] args) {
    System.out.println("Enter number of students: ");
    Scanner scanner = new Scanner(System.in);
    int threads = scanner.nextInt();
    scanner.close();
    Classroom classroom = new Classroom();
    //noinspection InfiniteLoopStatement
    while (true) {
      System.out.println("\nExam started\n");
      List<ExamResult> results = classroom.takeExam(threads);
      System.out.println();
      System.out.println("=".repeat(50));
      results.forEach(System.out::println);
      System.out.println("=".repeat(50));
      try {
        //noinspection BusyWait
        Thread.sleep(6000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}