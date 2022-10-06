package com.oop;

import com.oop.engines.*;
import com.oop.menu.*;
import java.util.ArrayList;
import java.util.List;
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
   * Engines list
   */
  private static final ArrayList<Engine> ENGINES_LIST = new ArrayList<>();
  /**
   * Menu to select engine
   */
  private static Menu engineSelectMenu;

  /**
   * Entry point
   */
  public static void main(String[] args) {
    MenuBuilder builder = new MenuBuilder();
    engineSelectMenu = builder
            .withDescription("Select engine to add")
            .add("Jet engine", Main::addJetEngine)
            .add("Diesel engine", Main::addDieselEngine)
            .build();

    Menu menu = builder.clear()
           .withHeader(MENU_END).withFooter(MENU_END)
           .withDescription("Select available option")
           .add("Add engine", Main::selectEngineToAdd)
           .add("Remove engine", Main::removeEngine)
           .add("Display engines", Main::displayEngines)
           .add("Compare two engines", Main::compareEngines)
           .build();
    //noinspection StatementWithEmptyBody
    while (!menu.show(SCANNER, true));
    SCANNER.close();
  }

  /**
   * Prompts user to select engine
   */
  private static void selectEngineToAdd() {
    engineSelectMenu.show(SCANNER, false);
  }

  /**
   * Adds the jet engine to list
   */
  private static void addJetEngine() {
    String manufacturer = retrieveString("Enter manufacturer: ");
    int enginePower = retrieve("Enter engine power: ", SCANNER::hasNextInt, Integer::parseInt);
    String fuelType = retrieveString("Enter fuel type: ");
    int maxFuel = retrieve("Enter max fuel: ", SCANNER::hasNextInt, Integer::parseInt);
    String gasType = retrieveString("Enter gas type: ");
    int gasVolume = retrieve("Enter gas volume: ", SCANNER::hasNextInt, Integer::parseInt);
    JetEngine jetEngine = new JetEngine(manufacturer, enginePower, fuelType, maxFuel, gasType, gasVolume);
    ENGINES_LIST.add(jetEngine);
    System.out.println("Jet engine added");
  }

  /**
   * Adds the diesel engine to list
   */
  private static void addDieselEngine() {
    String manufacturer = retrieveString("Enter manufacturer: ");
    int enginePower = retrieve("Enter engine power: ", SCANNER::hasNextInt, Integer::parseInt);
    String fuelType = retrieveString("Enter fuel type: ");
    int maxFuel = retrieve("Enter max fuel: ", SCANNER::hasNextInt, Integer::parseInt);
    int strokes = retrieve("Enter strokes: ", SCANNER::hasNextInt, Integer::parseInt);
    DieselEngine dieselEngine = new DieselEngine(manufacturer, enginePower, fuelType, maxFuel, strokes);
    ENGINES_LIST.add(dieselEngine);
    System.out.println("Diesel engine was added");
  }

  /**
   * Removes the engine from list
   */
  private static void removeEngine() {
    if (ENGINES_LIST.isEmpty()) {
      System.out.println("No engines in list");
      return;
    }
    int index = retrieveIndex(ENGINES_LIST);
    ENGINES_LIST.remove(index);
    System.out.println("Engine was removed");
  }

  /**
   * Displays all engines in list
   */
  private static void displayEngines() {
    if (ENGINES_LIST.isEmpty()) {
      System.out.println("No engines in list");
      return;
    }
    for (Engine engine : ENGINES_LIST) {
      System.out.println(engine.toString());
    }
  }

  /**
   * Compares two engines
   */
  private static void compareEngines() {
    Engine lhs = ENGINES_LIST.get(retrieveIndex(ENGINES_LIST));
    System.out.println(lhs.toString());
    Engine rhs = ENGINES_LIST.get(retrieveIndex(ENGINES_LIST));
    System.out.println(rhs.toString());
    System.out.format("These two engines are%s equal\n", lhs.equals(rhs) ? " " : " not");
  }

  /**
   * Prompts user to input required data
   * @param prompt shown before requesting input
   * @param validator tests if provided input is valid
   * @param parser if input is valid, parse it
   * @return user-provided data
   * @param <T> requested data type
   */
  private static <T> T retrieve(String prompt,
                                Supplier<Boolean> validator,
                                Function<String, T> parser) {
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

  /**
   * Promots user to input element's index in list
   * @param list the list with elements
   * @return zero-based index selected by user
   * @param <T> the type of elements in list
   */
  private static <T> int retrieveIndex(List<T> list) {
    int index;
    do {
      index = retrieve(String.format("Enter index in range 1-%d: ", list.size()),
                       SCANNER::hasNextInt, Integer::parseInt);
    } while (index < 0 || index >= list.size());
    return index - 1;
  }
}