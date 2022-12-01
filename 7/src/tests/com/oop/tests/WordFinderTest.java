package com.oop.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.oop.furniture.Furniture;
import org.junit.jupiter.api.Test;

class FurnitureTests {
  @Test
  public void test_emptyFurnitureCtor_mustReturnEmpty() {
    Furniture furniture = new Furniture();
    assertEquals(0, furniture.getCost());
    assertEquals(0f, furniture.getWeight());
    assertEquals("", furniture.getName());
    assertEquals("", furniture.getMaterial());
    assertTrue(furniture.canPickup());
  }

  @Test
  public void test_suppliedFurnitureCtor_mustReturnProvidedParams() {
    Furniture furniture = new Furniture(50, 10.5f, "Chair", "Iron");
    assertEquals(50, furniture.getCost());
    assertEquals(10.5f, furniture.getWeight());
    assertEquals("Chair", furniture.getName());
    assertEquals("Iron", furniture.getMaterial());
    assertTrue(furniture.canPickup());
  }

  @Test
  public void test_settingNewValues_mustReturnNewValues() {
    Furniture furniture = new Furniture();
    assertTrue(furniture.canPickup());
    furniture.setCost(1000);
    furniture.setWeight(40f);
    furniture.setName("Sofa");
    furniture.setMaterial("Wooden");
    assertEquals(1000, furniture.getCost());
    assertEquals(40f, furniture.getWeight());
    assertEquals("Sofa", furniture.getName());
    assertEquals("Wooden", furniture.getMaterial());
    assertFalse(furniture.canPickup());
  }
}
