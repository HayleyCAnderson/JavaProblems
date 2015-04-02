import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PersonPrinter {
  public Scanner reader;
  public String firstName;
  public String lastName;
  public String address;

  public PersonPrinter() {
    reader = new Scanner(System.in);
    firstName = new String();
    lastName = new String();
    address = new String();
  }

  public static void main(String[] args) {
    PersonPrinter printer = new PersonPrinter();
    printer.getAttributes(printer.chosenId());
    printer.printAttributes();
  }

  private int chosenId() {
    System.out.println("Which person id would you like to look up?");
    return reader.nextInt();
  }

  private void getAttributes(int chosenId) {
    Connection c = null;
    PreparedStatement stmt = null;

    try {
      // Preparing database
      Class.forName("org.postgresql.Driver");
      c = DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/testdb",
        "hand", "");
      c.setAutoCommit(false);

      // Creating SQL and executing search
      String sql = "SELECT * FROM person WHERE id=?";
      stmt = c.prepareStatement(sql);
      stmt.setInt(1, chosenId);
      ResultSet result = stmt.executeQuery();

      // Parsing results
      while (result.next()) {
        firstName = result.getString("first_name");
        lastName = result.getString("last_name");
        address = result.getString("address");
      }

      // Closing out database connection
      result.close();
      stmt.close();
      c.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void printAttributes() {
    System.out.println("1. First Name: " + firstName);
    System.out.println("2. Last Name: " + lastName);
    System.out.println("3. Address: " + address);
  }
}
