package com.oop.tasks;

import com.oop.menu.Menu;

public abstract class MenuTask implements ITask {
  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public final void run() {
    Menu menu = getMainMenu();
    while (!menu.show());
    menu.close();
  }

  /**
   * Get new main menu created by MenuBuilder
   * @return created main menu
   */
  protected abstract Menu getMainMenu();
}
