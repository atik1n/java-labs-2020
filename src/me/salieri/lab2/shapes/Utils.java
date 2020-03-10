package me.salieri.lab2.shapes;

public class Utils {
  static float distance(Point a, Point b)
  {
    return (float)Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
  }
}
