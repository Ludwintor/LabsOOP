package com.oop;

import com.oop.menu.Menu;
import com.oop.menu.MenuBuilder;
import com.oop.text.WordFinder;

import java.util.Random;
import java.util.Scanner;

/**
 * Entry point class
 */
public class Main {
  /**
   * Default edges for menu
   */
  private static final String DEFAULT_MENU_END = "=".repeat(50);
  /**
   * Ready-to-use random texts
   */
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

  /**
   * Scanner to read user input
   */
  private static final Scanner SCANNER = new Scanner(System.in);
  /**
   * Text randomizer
   */
  private static final Random RNG = new Random();
  /**
   * Find words in text
   */
  private static final WordFinder WORD_FINDER = new WordFinder();
  /**
   * Text to find words in
   */
  private static String text;
  /**
   * Substring that words should include
   */
  private static String substring;
  /**
   * Result of WordFinder
   */
  private static String result;

  /**
   * Entry point
   */
  public static void main(String[] args) {
    Scanner num = new Scanner(System.in);

    int columns, rows;

    System.out.print("Введите количество строк: ");
    rows = num.nextInt();
    System.out.print("Введите количество столбцов: ");
    columns = num.nextInt();

    int [][] mas = new int[rows][columns];

    // Инициализируем генератор
    Random random = new Random(101);

    // Матрица
    for (int i = 0; i < rows; i++){
      for (int j = 0; j < columns; j++){
        mas[i][j] = random.nextInt(100);
        System.out.print(mas[i][j]);
        System.out.print(' ');
      }
      System.out.println();
    }

    int n = 0, min = 999, max = 0;

    while (n != rows + columns - 1){
      for (int i = 0; i < rows; i++){
        for (int j = 0; j < columns; j++){
          if (i + j == n){
            if (min > mas[i][j]){
              min = mas[i][j];
            }
          }
        }
      }
      if (max < min){
        max = min;
      }
      min = 999;
      n++;
    }
    System.out.println(max);
    num.close();
  }

  /**
   * Get text and substring from user
   */
  private static void retrieveInputData() {
    result = null;
    System.out.println("Randomize text? [Y/n]");
    if (SCANNER.nextLine().equals("n"))
      manualTextInput();
    else
      generateText();
    System.out.println("Enter substring to search words");
    substring = SCANNER.nextLine();
  }

  /**
   * Get random text
   */
  private static void generateText() {
    text = TEXTS[RNG.nextInt(TEXTS.length)];
    System.out.println("Generated text\n"+text);
  }

  /**
   * Get text from user input
   */
  private static void manualTextInput() {
    System.out.print("Enter text: ");
    text = SCANNER.nextLine();
  }

  /**
   * Find words with substring and store in result
   */
  private static void findWordsWithSubstring() {
    if (text == null || substring == null) {
      System.out.println("Provide text and substring first");
      return;
    }
    result = WORD_FINDER.findWords(text, substring);
    System.out.println("Found words: "+result);
  }

  /**
   * Display text and result
   */
  private static void displayResult() {
    if (result == null) {
      System.out.println("Execute algorithm first");
      return;
    }
    System.out.println("Original text: "+text);
    System.out.println("\nFound words: "+result);
  }
}