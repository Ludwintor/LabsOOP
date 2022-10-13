package com.oop;

import com.oop.menu.*;
import com.oop.stringanalysers.ConsonantCounter;
import com.oop.stringanalysers.VowelCounter;

import java.util.Scanner;

/**
 * Entry point class
 */
public class Main {
  /**
   * Menu corners
   */
  private static final String MENU_END = "=".repeat(50);
  /**
   * Scanner instance
   */
  private static final Scanner SCANNER = new Scanner(System.in).useDelimiter("\\s+");

  private static String text;

  /**
   * Entry point
   */
  public static void main(String[] args) {
    MenuBuilder builder = new MenuBuilder();
    Menu menu = builder.clear()
           .withHeader(MENU_END).withFooter(MENU_END)
           .withDescription("Select available option")
           .add("Enter text", Main::enterText)
           .add("Find vowels", Main::findVowels)
           .add("Find consonants", Main::findConsonants)
           .build();
    //noinspection StatementWithEmptyBody
    while (!menu.show(SCANNER, true));
    SCANNER.close();
  }

  private static void enterText() {
    text = retrieveString("Enter text: ");
  }

  private static void findVowels() {
    int count = new VowelCounter().analyse(text);
    System.out.format("There are %d vowels in text", count);
  }

  private static void findConsonants() {
    int count = new ConsonantCounter().analyse(text);
    System.out.format("There are %d consonants in text", count);
  }

  /**
   * Prompts user to input string
   * @param prompt shown before requesting input
   * @return user-provided string
   */
  private static String retrieveString(String prompt) {
    System.out.print(prompt);
    return SCANNER.nextLine();
  }
}