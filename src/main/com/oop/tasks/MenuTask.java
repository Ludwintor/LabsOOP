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

  /**
   * Get new main menu created by MenuBuilder
   * @return created main menu
   */
  public abstract Menu getMainMenu();
}
