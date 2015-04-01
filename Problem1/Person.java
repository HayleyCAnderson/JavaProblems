import java.util.Scanner;

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
    person.print(firstName, lastName, address);
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

  private void print(String firstName, String lastName, String address) {
    XmlCreator xmlCreator = new XmlCreator();
    xmlCreator.printPerson(firstName, lastName, address);
    System.out.println("Check the tmp folder outside this folder for a print-out about you!");
  }
}
