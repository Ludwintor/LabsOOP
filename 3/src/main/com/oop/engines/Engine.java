package com.oop.engines;

import java.util.Objects;

/**
 * Represents engine
 */
public abstract class Engine {
  /**
   * Engine's manufacturer
   */
  private String manufacturer;
  /**
   * Engine's power
   */
  private int enginePower;

  public Engine() {
    manufacturer = "";
    enginePower = 0;
  }

  public Engine(String manufacturer, int enginePower) {
    setManufacturer(manufacturer);
    setEnginePower(enginePower);
  }

  public final String getManufacturer() {
    return manufacturer;
  }

  public final void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public final int getEnginePower() {
    return enginePower;
  }

  public final void setEnginePower(int enginePower) {
    this.enginePower = enginePower >= 0 ? enginePower : this.enginePower;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) return true;
    if (!(otherObject instanceof Engine other)) return false;
    return enginePower == other.enginePower && Objects.equals(manufacturer, other.manufacturer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, enginePower);
  }

  @Override
  public String toString() {
    return String.format("Manufacturer: %s  Engine power: %d", manufacturer, enginePower);
  }
}
