package me.salieri.lab4;

import me.salieri.lab4.DB.*;
import org.sqlite.core.DB;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    DBClass db = new DBClass();
    Scanner inScanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter how many rows will be created (int N): ");

      if (inScanner.hasNextLine()) {
        Scanner line = new Scanner(inScanner.nextLine());
        if (line.hasNextInt()) {
          DBUtils.initialize(db, line.nextInt());
          break;
        } else {
          System.out.println("Incorrect integer!");
        }
      }
    }

    System.out.println("Available commands:");
    for (String key : DBUtils.handlers.keySet()) {
      System.out.print(key + " ");
    }
    System.out.println();

    String cmd;
    while (inScanner.hasNextLine()) {
      DBUtils.execute(db, inScanner.nextLine());
    }

    inScanner.close();
    db.disconnect();
  }
}
