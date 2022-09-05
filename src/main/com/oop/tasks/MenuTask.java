package com.oop.tasks;

import com.oop.menu.Menu;
import java.io.IOException;

public abstract class MenuTask implements ITask {
    private Menu menu;

    @Override
    public final void run() {
        menu = getMainMenu();
        try {
            menu.show(true);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract Menu getMainMenu();
}
