package me.salieri.lab2.shapes;

public class Rectangle implements Polygon {
  public Rectangle(SinglePoint center, float width, float height)
  {
    if ((width <= 0) || (height <= 0)) {
      throw new IllegalArgumentException("Длина и/или ширина прямоугольника не могут быть меньше или равны нулю!");
    }
    this.center = center;
    this.width = width;
    this.height = height;
  }


  protected SinglePoint center;
  protected float width;
  protected float height;
  protected float rotAngle;


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
    return this.width * this.height;
  }

  @Override
  public void rotate(float angle) {
    float tmp = this.rotAngle + angle;
    this.rotAngle = tmp > 360 ? tmp - 360 : tmp;
  }

  @Override
  public void setRotation(float angle) {
    this.rotAngle = angle > 0 ? angle : 360 + angle;
  }

  @Override
  public float getRotation() {
    return this.rotAngle;
  }

  @Override
  public float getPerimeter() {
    return 2 * (width + height);
  }
}
