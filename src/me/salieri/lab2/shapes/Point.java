package me.salieri.lab2.shapes;

public interface Point {
  float getX();
  float getY();
  void moveTo(float x, float y);
  void moveTo(Point point);
  void moveBy(float x, float y);
  void moveBy(Point vector);
}
