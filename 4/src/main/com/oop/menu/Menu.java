package com.oop.menu;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Console menu
 */
public class Menu {
  /**
   * Menu header
   */
  private final String HEADER;
  /**
   * Menu description
   */
  private final String DESCRIPTION;
  /**
   * Menu footer
   */
  private final String FOOTER;
  /**
   * Menu executable items
   */
  private final Map<String, MenuItem> MENU_ITEMS;

  /**
   * Creates menu instance
   * @param header menu header
   * @param description menu description
   * @param footer menu footer
   * @param menuItems menu executable items
   */
  Menu(String header,
       String description,
       String footer,
       Map<String, MenuItem> menuItems) {
    HEADER = header;
    DESCRIPTION = description;
    FOOTER = footer;
    MENU_ITEMS = menuItems;
  }

  /**
   * Show menu
   * @param scanner gets user input to select menu item
   * @return true if user trying to exit from menu manually,
   * false otherwise (ex: one of the method executed successfully)
   */
  public boolean show(Scanner scanner, boolean pauseBeforeExit) {
    clearConsole();
    displayMenu();
    String selector;
    do {
      selector = scanner.nextLine().trim();
    } while (!MENU_ITEMS.containsKey(selector));
    clearConsole();
    MENU_ITEMS.get(selector).execute();
    if (pauseBeforeExit && !selector.equals("0")){
      printIfNotNull("\nPress Enter to continue");
      scanner.nextLine();
    }
    return selector.equals("0");
  }

  /**
   * Display whole menu
   */
  private void displayMenu() {
    printIfNotNull(HEADER);
    printIfNotNull(DESCRIPTION);
    MENU_ITEMS.forEach((key, value) -> System.out.println(key+". "+value.getName()));
    printIfNotNull(FOOTER);
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
