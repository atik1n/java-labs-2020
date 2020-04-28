package me.salieri.lab4;

import me.salieri.lab4.DB.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
  private static final Map<String, Method> handlers = new HashMap<String, Method>() {
    {
      try {
        put("add", DBClass.class.getDeclaredMethod("add", Scanner.class));
        put("delete", DBClass.class.getDeclaredMethod("delete", Scanner.class));
        put("show_all", DBClass.class.getDeclaredMethod("show_all", Scanner.class));
        put("price", DBClass.class.getDeclaredMethod("price", Scanner.class));
        put("change_price", DBClass.class.getDeclaredMethod("change_price", Scanner.class));
        put("filter_by_price", DBClass.class.getDeclaredMethod("filter_by_price", Scanner.class));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  };

  public static void main(String[] args) {
    DBClass db = new DBClass();
    Scanner inScanner = new Scanner(System.in);

    db.create();
    db.clear();

    while (true) {
      System.out.print("Enter how many rows will be created (int N): ");

      if (inScanner.hasNextLine()) {
        Scanner line = new Scanner(inScanner.nextLine());
        if (line.hasNextInt()) {
          for (int i = 0, n = line.nextInt(); i < n; i++) {
            int price = 100 + (int)(Math.random() * 101);
            line = new Scanner("prod" + i + " " + price);
            db.add(line);
          }
          break;
        } else {
          System.out.println("Incorrect integer!");
        }
      }
    }

    System.out.println("Available commands:");
    for (String key : handlers.keySet()) {
      System.out.print(key + " ");
    }
    System.out.println();

    String cmd;
    while (inScanner.hasNextLine()) {
      cmd = inScanner.nextLine();
      if (cmd.charAt(0) != '/') {
        System.out.println("Invalid syntax.");
        continue;
      }
      Scanner line = new Scanner(cmd.substring(1));

      if (line.hasNext()) {
        String key = line.next();
        if (!handlers.containsKey(key)) {
          System.out.println("Unknown command.");
          continue;
        }

        try {
          handlers.get(key).invoke(db, line);
        } catch (Exception e) {
          System.out.println("Exception occured in main thread! Stack trace:");
          e.printStackTrace();
        }
      }
    }

    inScanner.close();
    db.disconnect();
  }
}
