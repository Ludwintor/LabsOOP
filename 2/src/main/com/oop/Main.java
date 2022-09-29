package com.oop;

import com.oop.furniture.Furniture;
import com.oop.menu.Menu;
import com.oop.menu.MenuBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

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
   * Furniture list
   */
  private static final ArrayList<Furniture> FURNITURE_LIST = new ArrayList<>();
  /**
   * Menu for furniture editing
   */
  private static Menu editFurnitureMenu;
  /**
   * Menu for furniture list sorting
   */
  private static Menu sortMenu;
  /**
   * Current edited furniture (only set during editing)
   */
  private static Furniture editedFurniture;

  /**
   * Entry point
   */
  public static void main(String[] args) {
    MenuBuilder builder = new MenuBuilder();
    editFurnitureMenu = builder
           .withDescription("Select property to edit")
           .add("Cost", () -> editedFurniture.setCost(retrieve("Enter furniture cost: ",
                   SCANNER::hasNextInt, Integer::parseInt)))
           .add("Weight", () -> editedFurniture.setWeight(retrieve("Enter furniture weight: ",
                   SCANNER::hasNextFloat, Float::parseFloat, token -> token.replace(',', '.'))))
           .add("Name", () -> editedFurniture.setName(retrieveString("Enter furniture name: ")))
           .add("Material", () -> editedFurniture.setMaterial(retrieveString("Enter material: ")))
           .build();

    sortMenu = builder.clear()
           .withDescription("Select property to sort by")
           .add("Cost", () -> sort(Comparator.comparingInt(Furniture::getCost)))
           .add("Weight", () -> sort(Comparator.comparing(Furniture::getWeight)))
           .add("Name", () -> sort(Comparator.comparing(Furniture::getName)))
           .add("Material", () -> sort(Comparator.comparing(Furniture::getMaterial)))
           .build();

    Menu menu = builder.clear()
           .withHeader(MENU_END).withFooter(MENU_END)
           .withDescription("Select available option")
           .add("Add empty furniture", Main::addEmptyFurniture)
           .add("Add furniture", Main::addSuppliedFurniture)
           .add("Edit furniture", Main::editFurniture)
           .add("Display furniture", Main::displayFurniture)
           .build();
    //noinspection StatementWithEmptyBody
    while (!menu.show(SCANNER, true));
    SCANNER.close();
  }

  /**
   * Adds empty furniture to list
   */
  private static void addEmptyFurniture() {
    FURNITURE_LIST.add(new Furniture());
    System.out.println("Empty furniture added");
  }

  /**
   * Adds supplied by user furniture to list
   */
  private static void addSuppliedFurniture() {
    int cost = retrieve("Enter furniture cost: ", SCANNER::hasNextInt, Integer::parseInt);
    float weight = retrieve("Enter furniture weight: ", SCANNER::hasNextFloat,
            Float::parseFloat, token -> token.replace(',', '.'));
    String name = retrieveString("Enter furniture name: ");
    String material = retrieveString("Enter material: ");
    Furniture furniture = new Furniture(cost, weight, name, material);
    FURNITURE_LIST.add(furniture);
    System.out.println("Furniture added");
  }

  /**
   * Prompts user to edit one of furniture's property
   */
  private static void editFurniture() {
    System.out.println("Enter furniture index to edit");
    while (!SCANNER.hasNextInt()) {
      SCANNER.nextLine();
    }
    int index = Integer.parseInt(SCANNER.nextLine());
    editedFurniture = FURNITURE_LIST.get(index - 1);
    editFurnitureMenu.show(SCANNER, false);
    editedFurniture = null;
  }

  /**
   * Displays all furniture in list
   */
  private static void displayFurniture() {
    String header = String.format("%-14s %-14s %-9s %-9s %-14s",
                                  "Name", "Material", "Cost", "Weight", "Lift by hands?");
    String selector;
    do {
      System.out.println(header);
      for (Furniture furniture : FURNITURE_LIST) {
        System.out.format("%-14s %-14s %-9d %-9.1f %-14s\n",
                furniture.getName(), furniture.getMaterial(),
                furniture.getCost(), furniture.getWeight(),
                furniture.canPickup() ? "Yes" : "No");
      }
      System.out.println("\nSort list? (y/N)");
      selector = SCANNER.nextLine().toLowerCase();
    } while (selector.equals("y") && !sortMenu.show(SCANNER, false));
  }

  /**
   * Sorts furniture list
   * @param comparator comparing property of two furniture to sort by it
   */
  private static void sort(Comparator<Furniture> comparator) {
    FURNITURE_LIST.sort(comparator);
  }

  /**
   * Prompts user to input required data
   * @param prompt shown before requesting input
   * @param validator tests if provided input is valid
   * @param parser if input is valid, parse it
   * @param tokenProcessors modify input before parsing it
   * @return user-provided data
   * @param <T> requested data type
   */
  @SafeVarargs
  private static <T> T retrieve(String prompt,
                                Supplier<Boolean> validator,
                                Function<String, T> parser,
                                Function<String, String>... tokenProcessors) {
    System.out.print(prompt);
    while (!validator.get())
      SCANNER.nextLine();

    String token = SCANNER.nextLine();
    for (Function<String, String> processor : tokenProcessors)
      token = processor.apply(token);
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