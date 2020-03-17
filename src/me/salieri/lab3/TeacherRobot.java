package me.salieri.lab3;

public class TeacherRobot extends Thread implements Robot {
  final protected int maxStudents = 10;
  final protected int labsPerTick = 5;
  final String prefix;


  public TeacherRobot(Subject subject, RobotQueue queue, String name)
  {
    super();
    this.subject = subject;
    this.queue = queue;
    this.setName(name);
    this.prefix = String.format("[ROBO] %s | %s: ", subject, name);
    System.out.println(prefix + "готов к работе.");
  }


  private Subject subject;
  private boolean closed = false;
  private RobotQueue queue;

  @Override
  public void run()
  {
    try {
      System.out.println(prefix + "приём работ по " + subject + " начат.");
      while (!closed) {
        Student student = null;
        try {
          while ((student == null) && !closed) {
            student = queue.poll(subject);
          }
          if (closed) {
            break;
          }

          sleep(student.getLabs() / labsPerTick * 100);
          System.out.println(prefix + "принято " + student.getLabs() + " лабораторных.");
        } catch (NullPointerException e) {
          System.out.println(prefix + "Nullpo! Зашел на @channel.");
        }
      }
      System.out.println(prefix + "работа завершена.");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setClosed(boolean closed)
  {
    this.closed = closed;
  }
}
