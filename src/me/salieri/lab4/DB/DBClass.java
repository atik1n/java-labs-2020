package me.salieri.lab4.DB;

import com.sun.source.tree.ReturnTree;

import java.sql.*;
import java.util.Scanner;

public class DBClass extends ADBClass {
  private Connection connection;
  private PreparedStatement statement;
  private String query; // Я просто не хочу вечно создавать ради этого строчку
  private final String dbName = "lab4";
  private final String dbTable = "goods";
  private final String dbTableStruct = "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
      "prodid INTEGER NOT NULL UNIQUE," +
      "title VARCHAR NOT NULL UNIQUE," +
      "price INTEGER NOT NULL";

  public DBClass() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:res\\" + dbName + ".sqlite");
      if (connection.isValid(1)) {
        System.out.println("Connection established.");
      }
    } catch (SQLException e) {
      printException(e);
    }
  }

  @Override
  public void create() {
    query = "CREATE TABLE IF NOT EXISTS " + dbTable + "(" + dbTableStruct + ")";
    executeQuery("create");
  }

  @Override
  public void clear() {
    query = "DROP TABLE " + dbTable;
    executeQuery("clear");
    create();
  }

  @Override
  public void disconnect() {
    try {
      connection.close();
    } catch (SQLException e) {
      printException(e, "disconnect");
    }
  }


  @Override
  public void add(Scanner s) {
    String title;
    if (s.hasNext()) {
      title = s.next();
    } else {
      System.out.println("Incorrect title!");
      return;
    }

    int price;
    if (s.hasNextInt()) {
      price = s.nextInt();
    } else {
      System.out.println("Incorrect price!");
      return;
    }

    addItem(title, price);
  }

  @Override
  public void delete(Scanner s) {
    if (s.hasNext()) {
      deleteItem(s.next());
    } else {
      System.out.println("Incorrect title!");
    }
  }

  @Override
  public void show_all(Scanner s) {
    query = "SELECT prodid, title, price FROM " + dbTable;
    try {
      statement = connection.prepareStatement(query);
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        System.out.print(rs.getString(2));
        System.out.print("(" + rs.getInt(1) + "): ");
        System.out.println(rs.getInt(3) + " rubs.");
      }
    } catch (SQLException e) {
      printException(e, "show_all");
    }
  }

  @Override
  public void price(Scanner s) {
    query = "SELECT price FROM " + dbTable + " WHERE title = ? LIMIT 1";
    try {
      statement = connection.prepareStatement(query);

      if (s.hasNext()) {
        statement.setString(1, s.next());
      } else {
        System.out.println("Incorrect title!");
        return;
      }

      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        System.out.println(rs.getInt(1) + " rubs.");
      } else {
        System.out.println("No such goods with given title.");
      }
    } catch (SQLException e) {
      printException(e, "price");
    }
  }

  @Override
  public void change_price(Scanner s) {
    query = "UPDATE " + dbTable + " SET price = ? WHERE title = ?";
    try {
      statement = connection.prepareStatement(query);

      if (s.hasNext()) {
        statement.setString(2, s.next());
      } else {
        System.out.println("Incorrect title!");
        return;
      }

      if (s.hasNextInt()) {
        statement.setInt(1, s.nextInt());
      } else {
        System.out.println("Incorrect price!");
        return;
      }

      statement.execute();
    } catch (SQLException e) {
      printException(e, "change_price");
    }
  }

  @Override
  public void filter_by_price(Scanner s) {
    query = "SELECT prodid, title, price FROM " + dbTable + " WHERE price BETWEEN ? AND ?";
    try {
      statement = connection.prepareStatement(query);

      if (s.hasNextInt()) {
        statement.setInt(1, s.nextInt());
      } else {
        System.out.println("Incorrect price!");
        return;
      }

      if (s.hasNextInt()) {
        statement.setInt(2, s.nextInt());
      } else {
        System.out.println("Incorrect price!");
        return;
      }

      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        System.out.print(rs.getString(2));
        System.out.print("(" + rs.getInt(1) + "): ");
        System.out.println(rs.getInt(3) + " rubs.");
      }
    } catch (SQLException e) {
      printException(e, "filter_by_price");
    }
  }

  public void addItem(String title, int price) {
    query = "INSERT INTO " + dbTable + "(prodid, title, price) VALUES (?, ?, ?)";
    String subQuery = "SELECT EXISTS(SELECT title FROM " + dbTable + " WHERE title = ?)";
    try {
      statement = connection.prepareStatement(query);
      PreparedStatement subStatement = connection.prepareStatement(subQuery);

      int prodid = title.hashCode();
      statement.setInt(1, prodid);
      statement.setString(2, title);
      subStatement.setString(1, title);

      statement.setInt(3, price);

      ResultSet rs = subStatement.executeQuery();
      if (!rs.getBoolean(1)) {
        statement.execute();
      } else {
        System.out.println("There is existing goods with given title.");
      }
    } catch (SQLException e) {
      printException(e, "addItem");
    }
  }

  public void deleteItem(String title) {
    query = "DELETE FROM " + dbTable + " WHERE title = ?";
    try {
      statement = connection.prepareStatement(query);
      statement.setString(1, title);

      statement.execute();
    } catch (SQLException e) {
      printException(e, "delete");
    }
  }

  public ResultSet getByQuery(String query) {
    try {
      statement = connection.prepareStatement(query);
      return statement.executeQuery();
    } catch (SQLException e) {
      printException(e, "getByQuery");
      return null;
    }
  }


  private void executeQuery(String caller) {
    try {
      statement = connection.prepareStatement(query);
      statement.execute();
    } catch (SQLException e) {
      printException(e, caller);
    }
  }

  private void executeQuery() {
    try {
      statement = connection.prepareStatement(query);
      statement.execute();
    } catch (SQLException e) {
      printException(e);
    }
  }

  private void printException(SQLException e, String caller) {
    System.out.println("Exception occurred in \"" + caller + "\"! Stack trace:");
    e.printStackTrace();
  }

  private void printException(SQLException e) {
    System.out.println("Exception occurred! Stack trace:");
    e.printStackTrace();
  }
}
