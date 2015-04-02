import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableCreator {
  public static void main(String[] args) {
    Connection c = null;
    Statement stmt = null;

    try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/testdb",
        "hand", "");

      // Creating table
      String sql = "CREATE TABLE person " +
                   "(id SERIAL PRIMARY KEY     NOT NULL, " +
                   "first_name        TEXT     NOT NULL, " +
                   "last_name         TEXT     NOT NULL, " +
                   "address           TEXT     NOT NULL)";

      // Executing and closing out update
      stmt = c.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
