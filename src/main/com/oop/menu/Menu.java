package com.oop.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Menu {
    private final BufferedReader reader;
    private final String header;
    private final String description;
    private final String footer;
    private final HashMap<String, MenuItem> menuItems;

    Menu(BufferedReader reader,
                String header,
                String description,
                String footer,
                HashMap<String, MenuItem> menuItems) {
        this.reader = reader;
        this.header = header;
        this.description = description;
        this.footer = footer;
        this.menuItems = menuItems;
    }
    
    public void show(boolean reshowAfterExecution) throws IOException {
        String selector;
        do {
            displayMenu();
            selector = reader.readLine();
            retrieveMenuItem(selector).execute();
        } while (reshowAfterExecution && !selector.equals("0"));
    }

    private MenuItem retrieveMenuItem(String selector) throws IOException {
        while (!menuItems.containsKey(selector))
            selector = reader.readLine();
        return menuItems.get(selector);
    }

    private void displayMenu() {
        printIfNotNull(header);
        printIfNotNull(description);
        menuItems.forEach((key, value) -> System.out.println(key+". "+value.getName()));
        printIfNotNull(footer);
    }

    private void printIfNotNull(String str) {
        if (str != null)
            System.out.println(str);
    }
}
