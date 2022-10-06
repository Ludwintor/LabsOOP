package com.oop.engines;

import java.util.Objects;

public class JetEngine extends InternalCombustionEngine {
  private String gasType;
  private int gasVolume;

  public JetEngine() {
    super();
    gasType = "";
    gasVolume = 0;
  }

  public JetEngine(String manufacturer, int enginePower, String fuelType, int maxFuel, String gasType, int gasVolume) {
    super(manufacturer, enginePower, fuelType, maxFuel);
    setGasType(gasType);
    setGasVolume(gasVolume);
  }

  public String getGasType() {
    return gasType;
  }

  public void setGasType(String gasType) {
    this.gasType = gasType;
  }

  public int getGasVolume() {
    return gasVolume;
  }

  public void setGasVolume(int gasVolume) {
    this.gasVolume = gasVolume >= 0 ? gasVolume : this.gasVolume;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) return true;
    if (!(otherObject instanceof JetEngine other)) return false;
    if (!super.equals(otherObject)) return false;
    return gasVolume == other.gasVolume && Objects.equals(gasType, other.gasType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), gasType, gasVolume);
  }

  @Override
  public String toString() {
    return super.toString() + String.format("  Gas type: %s  Gas volume: %d", gasType, gasVolume);
  }
}
