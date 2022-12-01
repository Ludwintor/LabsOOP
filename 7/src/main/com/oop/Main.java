package com.oop;

import com.oop.furniture.Furniture;
import com.oop.menu.Menu;
import com.oop.menu.MenuBuilder;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Entry point class
 */
public class Main {
  /**
   * Menu corners
   */
  private static final String MENU_END = "=".repeat(50);
  /**
   * Furniture display header
   */
  private static final String HEADER = String.format("%-14s %-14s %-9s %-9s %-14s",
          "Name", "Material", "Cost", "Weight", "Lift by hands?");
  /**
   * Scanner instance
   */
  private static final Scanner SCANNER = new Scanner(System.in).useDelimiter("\\s+");
  /**
   * Furniture list
   */
  private static List<Furniture> furnitureList = new ArrayList<>();
  /**
   * Menu for furniture editing
   */
  private static Menu editFurnitureMenu;
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
           .add("Cost", () -> {
             int cost = retrieve("Enter furniture cost: ", SCANNER::hasNextInt, Integer::parseInt);
             editedFurniture.setCost(cost);
           })
           .add("Weight", () -> {
             float weight = retrieve("Enter furniture weight: ", SCANNER::hasNextFloat, Float::parseFloat);
             editedFurniture.setWeight(weight);
           })
           .add("Name", () -> editedFurniture.setName(retrieveString("Enter furniture name: ")))
           .add("Material", () -> editedFurniture.setMaterial(retrieveString("Enter material: ")))
           .build();

    Menu menu = builder.clear()
           .withHeader(MENU_END).withFooter(MENU_END)
           .withDescription("Select available option")
           .add("Add empty furniture", Main::addEmptyFurniture)
           .add("Add furniture", Main::addSuppliedFurniture)
           .add("Edit furniture", Main::editFurniture)
           .add("Display furniture", Main::displayFurniture)
           .add("Filter furniture", Main::filterByCost)
           .add("Remove duplicate furniture", Main::removeDuplicate)
           .add("Count furniture costs", Main::sumCosts)
           .add("Display non-blank names", Main::displayNonBlankNames)
           .add("Group by material", Main::groupByMaterial)
           .add("Summary statistics", Main::summaryStatistics)
           .build();
    furnitureList.add(new Furniture(100, 3f, "Chair", "Wooden"));
    furnitureList.add(new Furniture(200, 8f, "Table", "Wooden"));
    furnitureList.add(new Furniture(1000, 40f, "Shelf", "Iron"));
    furnitureList.add(new Furniture(0, 0f, "", ""));
    furnitureList.add(new Furniture(1000, 40f, "Shelf", "Iron"));
    furnitureList.add(new Furniture(300, 0.5f, "Lamp", "Plastic"));
    //noinspection StatementWithEmptyBody
    while (!menu.show(SCANNER, true));
    SCANNER.close();
  }

  /**
   * Adds empty furniture to list
   */
  private static void addEmptyFurniture() {
    furnitureList.add(new Furniture());
    System.out.println("Empty furniture added");
  }

  /**
   * Adds supplied by user furniture to list
   */
  private static void addSuppliedFurniture() {
    int cost = retrieve("Enter furniture cost: ", SCANNER::hasNextInt, Integer::parseInt);
    float weight = retrieve("Enter furniture weight: ", SCANNER::hasNextFloat, Float::parseFloat);
    String name = retrieveString("Enter furniture name: ");
    String material = retrieveString("Enter material: ");
    Furniture furniture = new Furniture(cost, weight, name, material);
    furnitureList.add(furniture);
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
    editedFurniture = furnitureList.get(index - 1);
    editFurnitureMenu.show(SCANNER, false);
    editedFurniture = null;
  }

  /**
   * Displays all furniture in list
   */
  private static void displayFurniture() {
    printFurniture(furnitureList.stream());
  }

  /**
   * Displays list filtered by cost
   */
  private static void filterByCost() {
    int cost = retrieve("Enter minimum cost: ", SCANNER::hasNextInt, Integer::parseInt);
    Stream<Furniture> filtered = furnitureList.stream().filter(furniture -> furniture.getCost() >= cost);
    printFurniture(filtered);
  }

  /**
   * Removes duplicates from list
   */
  private static void removeDuplicate() {
    furnitureList = furnitureList.stream().distinct().collect(Collectors.toList());
    displayFurniture();
  }

  /**
   * Displays sum of all costs
   */
  private static void sumCosts() {
    int sum = furnitureList.stream().mapToInt(Furniture::getCost).sum();
    System.out.format("Sum of all costs: %d\n", sum);
  }

  /**
   * Displays all non blank names
   */
  private static void displayNonBlankNames() {
    furnitureList.stream().map(furniture -> furniture.getName().isEmpty() ? Optional.empty() : Optional.of(furniture.getName()))
            .forEach(optional -> optional.ifPresent(System.out::println));
  }

  /**
   * Groups furniture by materials and counts it
   */
  private static void groupByMaterial() {
    Map<String, Long> groups = furnitureList.stream().collect(Collectors.groupingBy(Furniture::getMaterial, Collectors.counting()));
    groups.entrySet().forEach(System.out::println);
  }

  /**
   * Displays summary statistics on cost
   */
  private static void summaryStatistics() {
    IntSummaryStatistics stats = furnitureList.stream().mapToInt(Furniture::getCost).summaryStatistics();
    float average = (float)stats.getAverage();
    int min = stats.getMin();
    int max = stats.getMax();
    System.out.format("Average cost: %f\n", average);
    System.out.format("Min cost: %d\n", min);
    System.out.format("Max cost: %d\n", max);
  }

  /**
   * Displays stream of furniture
   * @param furnitureStream furniture stream
   */
  private static void printFurniture(Stream<Furniture> furnitureStream) {
    System.out.println(HEADER);
    Consumer<Furniture> printer = (Furniture furniture) -> {
      System.out.format("%-14s %-14s %-9d %-9.1f %-14s\n",
              furniture.getName(), furniture.getMaterial(),
              furniture.getCost(), furniture.getWeight(),
              furniture.canPickup() ? "Yes" : "No");
    };
    furnitureStream.forEach(printer);
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
    while (token.isEmpty())
      token = SCANNER.nextLine();
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