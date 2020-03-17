package me.salieri.lab3;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import static java.lang.Thread.sleep;

public class RobotQueue {
  private final int teachersCount = 3;
  public final int maxStudents = 10;
  private final InstructorRobot instructor;


  public RobotQueue() throws InterruptedException
  {
    Subject[] subjects = Subject.values();
    for (Subject subject : subjects) {
      students.put(subject, new ConcurrentLinkedQueue<>());
    }

    TeacherRobot physRobot = new TeacherRobot(Subject.PHYS, this, "Поповнатор");
    TeacherRobot progRobot = new TeacherRobot(Subject.PROG, this, "Мартынатор");
    TeacherRobot mathRobot = new TeacherRobot(Subject.MATH, this, "Подсыпатор");
    teachers.put(Subject.PHYS, physRobot);
    teachers.put(Subject.PROG, progRobot);
    teachers.put(Subject.MATH, mathRobot);

    instructor = new InstructorRobot(this);
    instructor.start();
    sleep(2000);

    physRobot.start();
    progRobot.start();
    mathRobot.start();
  }


  private Map<Subject, Queue<Student>> students = new HashMap<>(3);
  private Map<Subject, TeacherRobot> teachers = new HashMap<>(teachersCount);
  private boolean closed = false;


  public void add(Student student, boolean notify) throws InterruptedException
  {
    students.get(student.getSubject()).add(student);
    if (notify) {
      synchronized (teachers.get(student.getSubject())) {
        teachers.get(student.getSubject()).notify();
      }
    }

    System.out.println(String.format("[STUD] %s | Лабораторных: %s", student.getSubject(), student.getLabs()));

    if (getSize() >= maxStudents) {
      synchronized (instructor) {
        instructor.wait();
      }
    }
  }

  public Student poll(Subject subject) throws InterruptedException {
    Student student;
    synchronized (teachers.get(subject)) {
      if (students.get(subject).size() > 0) {
        student = students.get(subject).poll();
      } else {
        student = null;

        if (closed) {
          teachers.get(subject).setClosed(true);
        } else {
          System.out.println("[ROBO] " + subject + " | " + teachers.get(subject).getName() + ": простаивает.");
          teachers.get(subject).wait();
        }
      }
    }

    synchronized (instructor) {
      if (!closed) {
        instructor.notify();
      }
    }

    return student;
  }

  public Queue<Student> getQueue(Subject subject)
  {
    return students.get(subject);
  }

  public int getSize()
  {
    int acc = 0;
    Subject[] subjects = Subject.values();
    for (Subject subject : subjects) {
      acc += students.get(subject).size();
    }
    return acc;
  }

  public void stop()
  {
    System.out.println("/// Гидрак закрыт из-за 2099-nCoV... ///");
    closed = true;

    synchronized (instructor) {
      instructor.setClosed(true);
      instructor.notify();
    }

    Subject[] subjects = Subject.values();
    for (Subject subject : subjects) {
      synchronized (teachers.get(subject)) {
        teachers.get(subject).notify();
      }
    }
  }
}
