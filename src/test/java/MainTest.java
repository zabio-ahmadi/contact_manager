
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import Application.Application;
import Contacts.Contacts;
import Contacts.Friend;

public class MainTest {

  Application app;

  public MainTest() {
    app = new Application();
  }

  @Test
  public void insertContactTest() {

    String name = "alex";
    List<String> lastName = List.of("kurteshi", "browman");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("alex@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=alex");
    String profession = "student";

    String type = "Friend";
    String relation = "close friend";

    Contacts contact = new Friend(name, lastName, type, relation, address, telephoneNumber, email, socialAcount,
        profession);

    int sizeBeforeAdd = app.getContactList().size();

    app.addToContactList(contact);

    // check size + 1
    assertEquals(app.getContactList().size(), 1 + sizeBeforeAdd);

    // check if present
    Optional<Contacts> filtered = app.getContactList()
        .stream()
        .filter(e -> e.getLastname().get(0).equals(lastName.get(0)))

        .findFirst();
    assertEquals(filtered.isPresent(), Boolean.TRUE);

    // check type
    assertEquals(filtered.get().getType(), contact.getType());
  }

  @Test
  public void DeleteContactTest() {

    String name = "alex";
    List<String> lastName = List.of("kurteshi", "browman");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("alex@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=alex");
    String profession = "student";

    String type = "Friend";
    String relation = Friend.contactRelation.get(0);

    Contacts contact = new Friend(name, lastName, type, relation, address, telephoneNumber, email, socialAcount,
        profession);
    int sizeBeforeAdd = app.getContactList().size();
    app.addToContactList(contact);

    // delete should return true if found
    assertEquals(app.delete(name), Boolean.TRUE);
    // the size should bee eqauls means delete works
    assertEquals(app.getContactList().size(), sizeBeforeAdd);

  }

  @Test
  public void sort() {

    String name = "alex";
    List<String> lastName = List.of("kurteshi", "browman");
    String address = "rue du rhone 4, 1203 Genève";
    List<String> telephoneNumber = List.of("+41 77 444 33 22");
    List<String> email = List.of("alex@gmail.com");
    List<String> socialAcount = List.of("https://hello.com/?name=alex");
    String profession = "student";
    String relation = "close freind";

    Contacts contact1 = new Friend(name, lastName, "Friend", relation, address, telephoneNumber, email, socialAcount,
        profession);

    String name2 = "AAAAAA";
    List<String> lastName2 = List.of("AAAAAA", "BBBBB");

    Contacts contact2 = new Friend(name2, lastName2, "Friend", relation, address, email, telephoneNumber, socialAcount,
        profession);

    app.addToContactList(contact1);
    app.addToContactList(contact2);

    // if sort is correct then "AAAAAA" should be at index 0
    assertEquals(app.getContactList().get(0).getName(), name2);

  }
}
