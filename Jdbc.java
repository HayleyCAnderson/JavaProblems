import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc {
  public static void main(String args[]) {
    Connection c = null;
    Statement stmt = null;

    try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/",
        "hand", "");

      // Creating database
      stmt = c.createStatement();
      String sql = "CREATE DATABASE testdb";
      stmt.executeUpdate(sql);

      // Closing connection
      stmt.close();
      c.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
