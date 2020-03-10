package me.salieri.lab2.shapes;

public class SinglePoint implements Point {
  public SinglePoint(float x, float y)
  {
    this.x = x;
    this.y = y;
  }

  public SinglePoint(Point point)
  {
    if (point == null) {
      throw new NullPointerException("Nullpo! SinglePoint от null!");
    }
    this.x = point.getX();
    this.y = point.getY();
  }


  private float x;
  private float y;


  @Override
  public float getX() {
    return this.x;
  }

  @Override
  public float getY() {
    return this.y;
  }

  @Override
  public void moveTo(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void moveTo(Point point) {
    this.x = point.getX();
    this.y = point.getY();
  }

  @Override
  public void moveBy(float x, float y) {
    this.x += x;
    this.y += y;
  }

  @Override
  public void moveBy(Point vector) {
    this.x += vector.getX();
    this.y += vector.getY();
  }
}
