package com.oop.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

final class MenuItem {
    private String name;
    private Runnable method;

     MenuItem(String name, Runnable method) {

        this.name = name;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    /**
     * Call supplied method
     */
    public void execute() {
        if (method != null)
            method.run();
    }
}
