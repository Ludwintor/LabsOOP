package com.oop.furniture;

/**
 * Represents furniture
 */
public class Furniture {
  /**
   * Maximum weight that can be picked by average person (kg)
   */
  public static final float PICKUP_THRESHOLD = 30f;
  /**
   * Furniture's cost
   */
  private int cost;
  /**
   * Furniture's weight
   */
  private float weight;
  /**
   * Furniture's name
   */
  private String name;
  /**
   * Furniture's material
   */
  private String material;

  /**
   * Creates empty furniture instance
   */
  public Furniture() {
    cost = 0;
    weight = 0;
    name = "";
    material = "";
  }

  /**
   * Creates supplied furniture instance
   * @param cost furniture cost
   * @param weight furniture weight
   * @param name furniture name
   * @param material furniture material
   */
  public Furniture(int cost, float weight, String name, String material) {
    this.cost = cost;
    this.weight = weight;
    this.name = name;
    this.material = material;
  }

  /**
   * Returns furniture's cost
   * @return furniture cost
   */
  public int getCost() {
    return cost;
  }

  /**
   * Sets new furniture's cost
   * @param cost new cost
   */
  public void setCost(int cost) {
    this.cost = cost;
  }

  /**
   * Returns furniture's weight
   * @return furniture weight
   */
  public float getWeight() {
    return weight;
  }

  /**
   * Sets new furniture's weight
   * @param weight new weight
   */
  public void setWeight(float weight) {
    this.weight = weight;
  }

  /**
   * Returns furniture's name
   * @return furniture name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets new furniture's name
   * @param name new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns furniture's material
   * @return furniture material
   */
  public String getMaterial() {
    return material;
  }

  /**
   * Sets new furniture's material
   * @param material new material
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * Can be picked by average person
   * @return true if can be picked by average person, otherwise false
   */
  public boolean canPickup() {
    return weight <= PICKUP_THRESHOLD;
  }
}
