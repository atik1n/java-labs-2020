package me.salieri.lab2.shapes;

public class Triangle implements Polygon {
  public Triangle(SinglePoint a, SinglePoint b, SinglePoint c)
  {
    double eps = 0.001;
    if (Utils.distance(a, b) + Utils.distance(a, c) - Utils.distance(b, c) < eps) {
      throw new IllegalArgumentException("Треугольник не может быть вырожденным!");
    }

    points = new SinglePoint[]{a, b, c};
  }


  protected SinglePoint[] points;
  protected float rotAngle;


  @Override
  public float getX() {
    float acc = 0;

    for (SinglePoint point : points) {
      acc += point.getX();
    }

    return acc / points.length;
  }

  @Override
  public float getY() {
    float acc = 0;

    for (SinglePoint point : points) {
      acc += point.getY();
    }

    return acc / points.length;
  }

  @Override
  public void moveTo(float x, float y) {
    float centerX = 0;
    float centerY = 0;

    for (SinglePoint point : points) {
      centerX += point.getX();
      centerY += point.getY();
    }
    centerX /= points.length;
    centerY /= points.length;

    float dx = x - centerX;
    float dy = y - centerY;

    for (SinglePoint point : points) {
      point.moveBy(dx, dy);
    }
  }

  @Override
  public void moveTo(Point point) {
    if (point == null) {
      throw new NullPointerException("Nullpo! SinglePoint от null!");
    }

    this.moveTo(point.getX(), point.getY());
  }

  @Override
  public void moveBy(float x, float y) {
    for (SinglePoint point : points) {
      point.moveBy(x, y);
    }
  }

  @Override
  public void moveBy(Point point) {
    if (point == null) {
      throw new NullPointerException("Nullpo! SinglePoint от null!");
    }

    this.moveBy(point.getX(), point.getY());
  }

  @Override
  public float getArea() {
    float lenA = Utils.distance(this.points[0], this.points[1]);
    float lenB = Utils.distance(this.points[1], this.points[2]);
    float lenC = Utils.distance(this.points[2], this.points[0]);
    float p = this.getPerimeter() / 2;

    return (float)Math.sqrt(p * (p - lenA) * (p - lenB) * (p - lenC));
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
    return Utils.distance(this.points[0], this.points[1]) +
           Utils.distance(this.points[1], this.points[2]) +
           Utils.distance(this.points[2], this.points[0]);
  }
}
