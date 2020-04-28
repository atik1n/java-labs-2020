package me.salieri.lab5;

import javax.swing.*;
import me.salieri.lab4.DB.*;
import me.salieri.lab4.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

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
    inScanner.close();

    JFrame frame = new JFrame("Lab5");
    MainGrid grid = new MainGrid();
    grid.db = db;
    grid.updateList();

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    SwingUtilities.updateComponentTreeUI(frame);
    frame.setContentPane(grid.mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(500, 600);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
