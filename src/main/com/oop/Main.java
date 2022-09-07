package com.oop;

import com.oop.tasks.ITask;
import com.oop.tasks.Task1;

public class Main {
  public static void main(String[] args) {
    ITask task = new Task1();
    task.run();
  }
}