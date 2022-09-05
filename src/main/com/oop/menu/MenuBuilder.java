package com.oop.menu;

import java.io.BufferedReader;
import java.util.HashMap;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class MenuBuilder {
    private final BufferedReader reader;
    private final HashMap<String, MenuItem> menuItems;
    private String header;
    private String description;
    private String footer;
    private String exitMenuName;

    public MenuBuilder(BufferedReader reader) {
        this.reader = reader;
        this.menuItems = new HashMap<String, MenuItem>();
        this.exitMenuName = "Exit";
    }

    /**
     * Add menu item to menu with custom selector
     * @param selector id for menu item activation<p>
     *               <b>Note:</b> "0" is reserved to exit from menu
     * @param name menu item name that will be shown in menu
     * @param method what will be called on activation.
     * @throws IllegalArgumentException if selector equals "0"
     */
    public MenuBuilder Add(String selector, String name, Runnable method) {
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
    public MenuBuilder Add(String name, Runnable method) {
        return Add(String.valueOf(menuItems.size() + 1), name, method);
    }

    /**
     * Remove menu item from menu
     * @param selector id for menu item activation
     */
    public MenuBuilder Remove(String selector) {
        menuItems.remove(selector);
        return this;
    }

    /**
     * Clear all menu items
     */
    public MenuBuilder Clear() {
        menuItems.clear();
        return this;
    }

    /**
     * Sets header that rendered before any menu item
     * @param header - new header to set
     */
    public MenuBuilder WithHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     * Sets description that rendered after footer
     * @param description - new description to set
     */
    public MenuBuilder WithDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets footer that rendered after all menu items
     * @param footer - new footer to set
     */
    public MenuBuilder WithFooter(String footer) {
        this.footer = footer;
        return this;
    }

    /**
     * Sets exit menu name for "0" selector. Default is "Exit"
     * @param exitMenuName new exit menu name to set
     */
    public MenuBuilder WithExitName(String exitMenuName) {
        this.exitMenuName = exitMenuName;
        return this;
    }

    /**
     * Build new menu with given settings
     */
    public Menu Build() {
        HashMap<String, MenuItem> items = new HashMap<>(menuItems);
        items.put("0", new MenuItem(exitMenuName, null));
        return new Menu(reader, header, description, footer, items);
    }
}
