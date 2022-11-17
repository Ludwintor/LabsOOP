package com.oop;

import com.oop.list.*;
import com.oop.menu.*;
import java.util.function.*;
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
  /**
   * Linked list with strings
   */
  @SuppressWarnings("FieldMayBeFinal")
  private static LinkedList<String> stringsList = new LinkedList<>();
  /**
   * Linked list with integers
   */
  @SuppressWarnings("FieldMayBeFinal")
  private static LinkedList<Integer> intsList = new LinkedList<>();
  /**
   * Menu for manipulating with list of strings
   */
  private static Menu stringsMenu;
  /**
   * Menu for manipulating with list of integers
   */
  private static Menu intsMenu;

  /**
   * Entry point
   */
  public static void main(String[] args) {
    MenuBuilder builder = new MenuBuilder();
    Menu menu = builder
           .withHeader(MENU_END).withFooter(MENU_END)
           .withDescription("Select available list")
           .add("Strings list", () -> stringsMenu.show(SCANNER, false))
           .add("Ints list", () -> intsMenu.show(SCANNER, false))
           .build();
    stringsMenu = builder.clear()
            .withDescription("Select available option")
            .add("Add as first", () -> add(stringsList, stringsList::addFirst, () -> true, (token) -> token))
            .add("Add as last", () -> add(stringsList, stringsList::addLast, () -> true, (token) -> token))
            .add("Add before node", () -> addRelative(stringsList, stringsList::addBefore, () -> true, (token) -> token))
            .add("Add after node", () -> addRelative(stringsList, stringsList::addAfter, () -> true, (token) -> token))
            .add("Remove before node", () -> remove(stringsList, stringsList::removeBefore))
            .add("Remove after node", () -> remove(stringsList, stringsList::removeAfter))
            .add("Swap neighbours of node", () -> swapNeighbours(stringsList))
            .build();
    intsMenu = builder.clear()
            .add("Add as first", () -> add(intsList, intsList::addFirst, SCANNER::hasNextInt, Integer::parseInt))
            .add("Add as last", () -> add(intsList, intsList::addLast, SCANNER::hasNextInt, Integer::parseInt))
            .add("Add before node", () -> addRelative(intsList, intsList::addBefore, SCANNER::hasNextInt, Integer::parseInt))
            .add("Add after node", () -> addRelative(intsList, intsList::addAfter, SCANNER::hasNextInt, Integer::parseInt))
            .add("Remove before node", () -> remove(intsList, intsList::removeBefore))
            .add("Remove after node", () -> remove(intsList, intsList::removeAfter))
            .add("Swap neighbours of node", () -> swapNeighbours(intsList))
            .build();
    //noinspection StatementWithEmptyBody
    while (!menu.show(SCANNER, true));
    SCANNER.close();
  }

  /**
   * Adds user-provided element to list
   * @param list linked list
   * @param adder method which adds new element to list
   * @param validator tests if provided input is valid
   * @param parser if input is valid, parse it
   * @param <T> type stored in list
   */
  private static <T> void add(LinkedList<T> list, Consumer<T> adder, Supplier<Boolean> validator,
                              Function<String, T> parser) {
    T item = retrieve("Enter new element: ", validator, parser);
    adder.accept(item);
    System.out.println(list);
  }

  /**
   * Adds user-provided element to list relative user-selected node
   * @param list linked list
   * @param adder method which adds new element to list relative node
   * @param validator tests if provided input is valid
   * @param parser if input is valid, parse it
   * @param <T> type stored in list
   */
  private static <T> void addRelative(LinkedList<T> list, BiConsumer<LinkedListItem<T>, T> adder,
                                      Supplier<Boolean> validator, Function<String, T> parser) {
    if (list.isEmpty()) {
      System.out.println("List is empty");
      return;
    }
    LinkedListItem<T> node = retrieveNode(list);
    T item = retrieve("Enter new element: ", validator, parser);
    adder.accept(node, item);
    System.out.println(list);
  }

  /**
   * Removes element from list relative user-selected node
   * @param list linked list
   * @param remover method which removes node from list
   * @param <T> type stored in list
   */
  private static <T> void remove(LinkedList<T> list, Consumer<LinkedListItem<T>> remover) {
    if (list.isEmpty()) {
      System.out.println("List is empty");
      return;
    }
    LinkedListItem<T> node = retrieveNode(list);
    remover.accept(node);
    System.out.println(list);
  }

  /**
   * Swap neighbors around user-selected node
   * @param list linked list
   * @param <T> type stored in list
   */
  private static <T> void swapNeighbours(LinkedList<T> list) {
    System.out.println(list);
    String prompt = String.format("Select node by index (1-%d): ", list.getCount());
    int index = retrieveInt(prompt, 1, list.getCount()) - 1;
    LinkedListItem<T> node = list.getNode(index);
    boolean success = list.trySwapNeighbours(node);
    System.out.println(success ? "Successfully swapped" : "Unable to swap");
    System.out.println(list);
  }

  /**
   * Retrieves node from user
   * @param list linked list
   * @return user-selected node
   * @param <T> type stored in list
   */
  private static <T> LinkedListItem<T> retrieveNode(LinkedList<T> list) {
    System.out.println(list);
    String prompt = String.format("Select node by index (1-%d): ", list.getCount());
    int index = retrieveInt(prompt, 1, list.getCount()) - 1;
    return list.getNode(index);
  }

  /**
   * Retrieve integer from user in provided range
   * @param prompt shown before requesting input
   * @param min minimum valid number
   * @param max maximum valid number
   * @return user-provided number in range
   */
  private static int retrieveInt(String prompt, int min, int max) {
    int result;
    do {
      result = retrieve(prompt, SCANNER::hasNextInt, Integer::parseInt);
    } while (result < min || result > max);
    return result;
  }

  /**
   * Prompts user to input required data
   * @param prompt shown before requesting input
   * @param validator tests if provided input is valid
   * @param parser if input is valid, parse it
   * @return user-provided data
   * @param <T> requested data type
   */
  private static <T> T retrieve(String prompt, Supplier<Boolean> validator, Function<String, T> parser) {
    System.out.print(prompt);
    while (!validator.get())
      SCANNER.nextLine();

    String token = SCANNER.nextLine();
    return parser.apply(token);
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