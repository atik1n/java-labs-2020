package me.salieri.lab2.shapes;

public class Circle implements Ellipse {
  public Circle(SinglePoint center, float radius)
  {
    if (radius <= 0) {
      throw new IllegalArgumentException("Радиус круга не может быть меньше или равен нулю!");
    }
    this.center = center;
    this.radius = radius;
  }

  public Circle(float x, float y, float radius)
  {
    this.center = new SinglePoint(x, y);
    this.radius = radius;
  }


  protected float radius;
  protected SinglePoint center;


  @Override
  public float getX() {
    return center.getX();
  }

  @Override
  public float getY() {
    return center.getY();
  }

  @Override
  public void moveTo(float x, float y) {
    center.moveTo(x, y);
  }

  @Override
  public void moveTo(Point point) {
    center.moveTo(point);
  }

  @Override
  public void moveBy(float x, float y) {
    center.moveBy(x, y);
  }

  @Override
  public void moveBy(Point point) {
    center.moveBy(point);
  }

  @Override
  public float getArea() {
    return (float)(Math.PI * Math.pow(this.radius, 2));
  }

  @Override
  public float getLength() {
    return 2 * (float)Math.PI * this.radius;
  }
}
