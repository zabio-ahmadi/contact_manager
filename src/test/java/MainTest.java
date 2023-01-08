
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import Application.Application;
import Contacts.Contacts;
import Friends.Friends;

public class MainTest {

  @Test
  public void insertContactTest() {

    Application app = new Application();

    String name = "agnon";
    List<String> lastName = List.of("kurteshi", "burteshi");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("agnon@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=agnon");
    String profession = "student";
    String friendSince = "2022/02/02";

    Contacts contact = new Friends(name, lastName, address, email, telephoneNumber, socialAcount, profession,
        friendSince);

    app.addToContactList(contact);

    assertEquals(app.getContactList().size(), 1);
    assertEquals(app.getContactList().get(0).getName(), name);
    assertEquals(app.getContactList().get(0).getType(), contact.getType());
    System.out.println("INSERTION TEST PASSED");

  }

  @Test
  public void DeleteContactTest() {

    Application app = new Application();

    String name = "agnon";
    List<String> lastName = List.of("kurteshi", "burteshi");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("agnon@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=agnon");
    String profession = "student";
    String friendSince = "2022/02/02";

    Contacts contact = new Friends(name, lastName, address, email, telephoneNumber, socialAcount, profession,
        friendSince);

    app.addToContactList(contact);

    app.delete(1);

    // after delete size should be 0
    assertEquals(app.getContactList().size(), 0);

    System.out.println("DELETION TEST PASSED");

  }

  @Test
  public void sort() {

    Application app = new Application();

    String name = "agnon";
    List<String> lastName = List.of("kurteshi", "burteshi");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("agnon@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=agnon");
    String profession = "student";
    String friendSince = "2022/02/02";

    Contacts contact1 = new Friends(name, lastName, address, email, telephoneNumber, socialAcount, profession,
        friendSince);

    String name2 = "AAAAAA";
    List<String> lastName2 = List.of("BBBBBBBBBBBBBBB", "BOURRRRRIS");

    Contacts contact2 = new Friends(name2, lastName2, address, email, telephoneNumber, socialAcount, profession,
        friendSince);

    app.addToContactList(contact1);
    app.addToContactList(contact2);

    // if sort is correct then "AAAAAA" should be at index 0
    assertEquals(app.getContactList().get(0).getName(), name2);
    System.out.println("SORT TEST PASSED");

  }
}
