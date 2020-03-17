package me.salieri.lab3;

import static java.lang.Thread.sleep;

public class Main {
  public static synchronized void main(String[] args) throws InterruptedException {
    RobotQueue queue = new RobotQueue();

    sleep(10000);
    queue.stop();
  }
}
