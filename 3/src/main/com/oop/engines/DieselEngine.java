package com.oop.engines;

import java.util.Objects;

public class DieselEngine extends InternalCombustionEngine {
  private int strokes;

  public DieselEngine() {
    super();
    strokes = 0;
  }

  public DieselEngine(String manufacturer, int enginePower, String fuelType, int maxFuel, int strokes) {
    super(manufacturer, enginePower, fuelType, maxFuel);
    setStrokes(strokes);
  }

  public int getStrokes() {
    return strokes;
  }

  public void setStrokes(int strokes) {
    this.strokes = strokes >= 0 ? strokes : this.strokes;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) return true;
    if (!(otherObject instanceof DieselEngine other)) return false;
    if (!super.equals(otherObject)) return false;
    return strokes == other.strokes;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), strokes);
  }

  @Override
  public String toString() {
    return super.toString() + String.format("  Strokes: %d", strokes);
  }
}
