package com.oop.engines;

import java.util.Objects;

public abstract class InternalCombustionEngine extends Engine {
  private String fuelType;
  private int maxFuel;

  public InternalCombustionEngine() {
    super();
    fuelType = "";
    maxFuel = 0;
  }

  public InternalCombustionEngine(String manufacturer, int enginePower, String fuelType, int maxFuel) {
    super(manufacturer, enginePower);
    setFuelType(fuelType);
    setMaxFuel(maxFuel);
  }

  public final String getFuelType() {
    return fuelType;
  }

  public final void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public final int getMaxFuel() {
    return maxFuel;
  }

  public final void setMaxFuel(int maxFuel) {
    this.maxFuel = maxFuel >= 0 ? maxFuel : this.maxFuel;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) return true;
    if (!(otherObject instanceof InternalCombustionEngine object)) return false;
    if (!super.equals(otherObject)) return false;
    return maxFuel == object.maxFuel && Objects.equals(fuelType, object.fuelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fuelType, maxFuel);
  }

  @Override
  public String toString() {
    return super.toString() + String.format("  Fuel type: %s  Max fuel: %d", fuelType, maxFuel);
  }
}
