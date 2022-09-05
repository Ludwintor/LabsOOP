package com.oop.tasks;

import com.oop.menu.Menu;
import com.oop.menu.MenuBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1 extends MenuTask {
    @Override
    public Menu getMainMenu() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MenuBuilder builder = new MenuBuilder(reader);
        String ending = "=".repeat(50);
        builder.WithHeader(ending).WithFooter(ending);
        builder.Add("1", "Enter text", () -> RetrieveInputData());

        Menu mainMenu = builder.Build();

        return mainMenu;
    }

    private void RetrieveInputData() {

    }
}

