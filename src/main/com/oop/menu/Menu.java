package com.oop.menu;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
  private final Scanner scanner;
  private final String header;
  private final String description;
  private final String footer;
  private final Map<String, MenuItem> menuItems;

  Menu(String header,
       String description,
       String footer,
       Map<String, MenuItem> menuItems) {
    scanner = new Scanner(System.in);
    this.header = header;
    this.description = description;
    this.footer = footer;
    this.menuItems = menuItems;
  }

  /**
   * Show menu
   * @return true if user trying to exit from menu manually,
   * false otherwise (ex: one of the method executed successfully)
   */
  public boolean show() {
    clearConsole();
    displayMenu();
    String selector;
    do {
      selector = scanner.nextLine().trim();
    } while (!menuItems.containsKey(selector));
    clearConsole();
    menuItems.get(selector).execute();
    if (!selector.equals("0")){
      printIfNotNull("\nPress Enter to continue");
      scanner.nextLine();
    }
    return selector.equals("0");
  }

  public void close() {
    scanner.close();
  }

  /**
   * Display whole menu
   */
  private void displayMenu() {
    printIfNotNull(header);
    printIfNotNull(description);
    menuItems.forEach((key, value) -> System.out.println(key+". "+value.getName()));
    printIfNotNull(footer);
  }

  /**
   * Print string if it doesn't null
   * @param str string to print
   */
  private void printIfNotNull(String str) {
    if (str != null)
      System.out.println(str);
  }

  /**
   * Clear console screen
   */
  private void clearConsole() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
