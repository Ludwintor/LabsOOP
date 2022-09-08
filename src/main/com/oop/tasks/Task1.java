package com.oop.tasks;

import com.oop.menu.Menu;
import com.oop.menu.MenuBuilder;
import com.oop.text.TextFinder;

import java.util.Random;
import java.util.Scanner;

public class Task1 extends MenuTask {
  private static final String[] TEXTS = {
    "Stronger unpacked felicity to of mistaken. Fanny at wrong table ye in. " +
    "Be on easily cannot innate in lasted months on. Differed and and felicity steepest mrs age outweigh. " +
    "Opinions learning likewise daughter now age outweigh. Raptures stanhill my " +
    "greatest mistaken or exercise he on although. " +
    "Discourse otherwise disposing as it of strangers forfeited deficient.",

    "Received overcame oh sensible so at an. Formed do change merely to county it. " +
    "Am separate contempt domestic to to oh. On relation my so addition branched. " +
    "Put hearing cottage she norland letters equally prepare too. Replied exposed savings " +
    "he no viewing as up. Soon body add him hill. No father living really people estate if. " +
    "Mistake do produce beloved demesne if am pursuit.",

    "Allow miles wound place the leave had. To sitting subject no improve studied limited. " +
    "Ye indulgence unreserved connection alteration appearance my an astonished. " +
    "Up as seen sent make he they of. Her raising and himself pasture believe females. " +
    "Fancy she stuff after aware merit small his. Charmed esteems luckily age out."
  };

  private final Scanner scanner;
  private final Random rng;
  private final TextFinder textFinder;
  private String text;
  private String substring;
  private String result;

  public Task1() {
    scanner = new Scanner(System.in);
    textFinder = new TextFinder();
    rng = new Random();
  }

  @Override
  protected Menu getMainMenu() {
    MenuBuilder builder = new MenuBuilder();
    String frame = "=".repeat(50);
    builder.withHeader(frame).withFooter(frame)
           .withDescription("Select available option")
           .add("Enter text and substring to find", this::retrieveInputData)
           .add("Find all words with provided substring", this::findWordsWithSubstring)
           .add("Display result", this::displayResult);
    return builder.build();
  }

  private void retrieveInputData() {
    result = null;
    System.out.println("Randomize text? [Y/n]");
    if (scanner.nextLine().equals("n"))
      manualTextInput();
    else
      generateText();
    System.out.println("Enter substring to search words");
    substring = scanner.nextLine();
  }

  private void generateText() {
    text = TEXTS[rng.nextInt(TEXTS.length)];
    System.out.println("Generated text\n"+text);
  }

  private void manualTextInput() {
    System.out.print("Enter text: ");
    text = scanner.nextLine();
  }

  private void findWordsWithSubstring() {
    if (text == null || substring == null) {
      System.out.println("Provide text and substring first");
      return;
    }
    result = textFinder.findWords(text, substring);
    System.out.println("Found words: "+result);
  }

  private void displayResult() {
    if (result == null) {
      System.out.println("Execute algorithm first");
      return;
    }
    System.out.println("Original text: "+text);
    System.out.println("\nFound words: "+result);
  }
}

