package com.oop.tasks;

import com.oop.menu.Menu;
import java.io.IOException;

public abstract class MenuTask implements ITask {
  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public final void run() {
    Menu menu = getMainMenu();
    while (!menu.show());
  }

  public abstract Menu getMainMenu();
}
