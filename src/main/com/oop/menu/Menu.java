package com.oop.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class Menu {
  private final BufferedReader reader;
  private final String header;
  private final String description;
  private final String footer;
  private final Map<String, MenuItem> menuItems;

  Menu(BufferedReader reader,
       String header,
       String description,
       String footer,
       Map<String, MenuItem> menuItems) {
    this.reader = reader;
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
    String selector = "";
    clearConsole();
    displayMenu();
    try {
      selector = reader.readLine();
      MenuItem item = retrieveMenuItem(selector);
      clearConsole();
      item.execute();
      if (!selector.equals("0")){
        printIfNotNull("\nPress Enter to continue");
        //noinspection ResultOfMethodCallIgnored
        reader.read();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return selector.equals("0");
  }

  /**
   * Request user input until receiving valid menu item
   * @param selector menu item selector
   * @return retrieved menu item
   * @throws IOException if an I/O error occur
   */
  private MenuItem retrieveMenuItem(String selector) throws IOException {
    while (!menuItems.containsKey(selector))
      selector = reader.readLine();
    return menuItems.get(selector);
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
