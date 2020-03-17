package me.salieri.lab3;

public class InstructorRobot extends Thread implements Robot {
  final private String prefix = "[ROBO] INST | Инструктор: ";

  public InstructorRobot(RobotQueue queue)
  {
    this.queue = queue;
    System.out.println(prefix + "готов к работе.");
  }


  private boolean closed = false;
  private RobotQueue queue;


  @Override
  public void run() {
    try {
      System.out.println(prefix + "начат запуск студентов.");
      while (!closed) {
        queue.add(Student.getRandomStudent(), true);
      }
      System.out.println(prefix + "запуск студентов окончен.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public synchronized void setClosed(boolean closed)
  {
    this.closed = closed;
  }
}
