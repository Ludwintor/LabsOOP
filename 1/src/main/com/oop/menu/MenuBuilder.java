package com.oop.menu;

import java.util.LinkedHashMap;

/**
 * Creates instance of Menu class
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class MenuBuilder {
  /**
   * Constant name for exit
   */
  private static final String EXIT_MENU_NAME = "Exit";
  /**
   * Container for menu items
   */
  private final LinkedHashMap<String, MenuItem> MENU_ITEMS = new LinkedHashMap<>();
  /**
   * Upper edge of menu
   */
  private String header;
  /**
   * Description before all menu items
   */
  private String description;
  /**
   * Lower edge of menu
   */
  private String footer;

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

    MENU_ITEMS.put(selector, new MenuItem(name, method));
    return this;
  }

  /**
   * Add menu item with arithmetic sequenced id
   * @param name menu item name that will be shown in menu
   * @param method what will be called on activation.
   */
  public MenuBuilder add(String name, Runnable method) {
    return add(String.valueOf(MENU_ITEMS.size() + 1), name, method);
  }

  /**
   * Remove menu item from menu
   * @param selector id for menu item activation
   */
  public MenuBuilder remove(String selector) {
    MENU_ITEMS.remove(selector);
    return this;
  }

  /**
   * Clear all menu items
   */
  public MenuBuilder clear() {
    MENU_ITEMS.clear();
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
   * Build new menu with given settings
   */
  public Menu build() {
    LinkedHashMap<String, MenuItem> items = new LinkedHashMap<>(MENU_ITEMS);
    items.put("0", new MenuItem(EXIT_MENU_NAME, null));
    return new Menu(header, description, footer, items);
  }
}
