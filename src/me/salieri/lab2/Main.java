package me.salieri.lab2;

import me.salieri.lab2.shapes.*;
import me.salieri.lab2.shapes.SinglePoint;

public class Main {
  public static float biggestArea(Shape[] shapes)
  {
    if (shapes == null) {
      throw new NullPointerException("Nullpo! Пустой массив фигур!");
    }

    float biggest = 0;

    for (Shape shape : shapes) {
      if (shape.getArea() > biggest) {
        biggest = shape.getArea();
      }
    }

    return biggest;
  }

  public static void main(String[] args) {
    Shape[] shapes = new Shape[]{
      new Circle(new SinglePoint(1, 1), 0.5f), // 0.7853
      new Rectangle(new SinglePoint(1, 2), 1, 2), // 2
      new Triangle(new SinglePoint(2, 2), new SinglePoint(2, 4), new SinglePoint(-1 ,-8)), // 3
      new Circle(new SinglePoint(1, 1), 1), // 3.1415
      new Rectangle(new SinglePoint(7, 2), 4, 2), // 8
      new Triangle(new SinglePoint(0, 2), new SinglePoint(2, 3), new SinglePoint(0 ,0)), // 2
      new Circle(new SinglePoint(-1, 1), 1.5f), // 7.0685
      new Rectangle(new SinglePoint(-1, 2), 3, 3), // 9
      new Triangle(new SinglePoint(1, 0), new SinglePoint(3, 3), new SinglePoint(-1, -4)), // 1
      new Circle(new SinglePoint(3, 4), 0.25f) // 0.1963
    };

    System.out.println(biggestArea(shapes));
  }
}
