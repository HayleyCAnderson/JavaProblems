import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Person {
  public Scanner reader;

  public Person() {
    reader = new Scanner(System.in);
  }

  public static void main(String[] args) {
    Person person = new Person();
    String firstName = person.firstName();
    String lastName = person.lastName();
    String address = person.address();
    person.createEntry(firstName, lastName, address);
  }

  private String firstName() {
    System.out.println("What is your first name?");
    return reader.nextLine();
  }

  private String lastName() {
    System.out.println("What is your last name?");
    return reader.nextLine();
  }

  private String address() {
    System.out.println("What is your address?");
    return reader.nextLine();
  }

  private void createEntry(String firstName, String lastName, String address) {
    Connection c = null;
    PreparedStatement stmt = null;

    try {
      // Preparing database
      Class.forName("org.postgresql.Driver");
      c = DriverManager
        .getConnection("jdbc:postgresql://localhost:5432/testdb",
        "hand", "");
      c.setAutoCommit(false);

      // Creating and executing SQL
      String sql = "INSERT INTO person (first_name, last_name, address) VALUES (?, ?, ?)";
      stmt = c.prepareStatement(sql);
      stmt.setString(1, firstName);
      stmt.setString(2, lastName);
      stmt.setString(3, address);
      stmt.executeUpdate();

      // Closing connection
      stmt.close();
      c.commit();
      c.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
