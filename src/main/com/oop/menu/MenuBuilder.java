package com.oop.menu;

import java.io.BufferedReader;
import java.util.LinkedHashMap;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class MenuBuilder {
  private final BufferedReader reader;
  private final LinkedHashMap<String, MenuItem> menuItems;
  private String header;
  private String description;
  private String footer;
  private String exitMenuName = "Exit";
  private boolean allowExit = true;

  public MenuBuilder(BufferedReader reader) {
    this.reader = reader;
    this.menuItems = new LinkedHashMap<>();
  }

  /**
   * Add menu item to menu with custom selector
   * @param selector id for menu item activation<p>
   *               <b>Note:</b> "0" is reserved to exit from menu
   * @param name menu item name that will be shown in menu
   * @param method what will be called on activation.
   * @throws IllegalArgumentException if selector equals "0"
   */
  public MenuBuilder add(String selector, String name, Runnable method) {
    if (selector.equals("0"))
      throw new IllegalArgumentException("Selector \"0\" is reserved for menu exit");

    menuItems.put(selector, new MenuItem(name, method));
    return this;
  }

  /**
   * Add menu item with arithmetic sequenced id
   * @param name menu item name that will be shown in menu
   * @param method what will be called on activation.
   */
  public MenuBuilder add(String name, Runnable method) {
    return add(String.valueOf(menuItems.size() + 1), name, method);
  }

  /**
   * Remove menu item from menu
   * @param selector id for menu item activation
   */
  public MenuBuilder remove(String selector) {
    menuItems.remove(selector);
    return this;
  }

  /**
   * Clear all menu items
   */
  public MenuBuilder clear() {
    menuItems.clear();
    return this;
  }

  /**
   * Sets header that rendered before any menu item
   * @param header - new header to set
   */
  public MenuBuilder withHeader(String header) {
    this.header = header;
    return this;
  }

  /**
   * Sets description that rendered after footer
   * @param description - new description to set
   */
  public MenuBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  /**
   * Sets footer that rendered after all menu items
   * @param footer - new footer to set
   */
  public MenuBuilder withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  /**
   * Sets exit menu name for "0" selector. Default is "Exit"
   * @param exitMenuName new exit menu name to set
   */
  public MenuBuilder withExitName(String exitMenuName) {
    this.exitMenuName = exitMenuName;
    return this;
  }

  public MenuBuilder allowExit(boolean allow) {
    allowExit = allow;
    return this;
  }

  /**
   * Build new menu with given settings
   */
  public Menu build() {
    LinkedHashMap<String, MenuItem> items = new LinkedHashMap<>(menuItems);
    if (allowExit)
      items.put("0", new MenuItem(exitMenuName, null));
    return new Menu(reader, header, description, footer, items);
  }
}
