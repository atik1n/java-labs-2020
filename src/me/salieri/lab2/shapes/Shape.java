package me.salieri.lab2.shapes;

public interface Shape extends Point {
  float getArea();
  default void rotate(float angle)
  {
  }
  default void setRotation(float angle)
  {
  }
  default float getRotation()
  {
    return 0;
  }
}
