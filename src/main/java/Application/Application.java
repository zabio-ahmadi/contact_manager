package Application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import Contacts.Contacts;
import Contacts.Family;
import Contacts.Friend;
import Contacts.Professional;

public class Application {
    public ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

    public Application() {
        this.loadDatabase();
        this.sortContactList();
    }

    public void saveContactList(ObservableList<Contacts> contactsList) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("db/contacts.json"), contactsList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseContact(Object contact) {
        JSONObject info = ((JSONObject) contact);

        String name = (String) info.get("name");

        List<String> lastname = Arrays.asList(info.get("lastname").toString()
                .replace("[", "")
                .replace("]", "")
                .trim()
                .replace("\"", "").split(","));

        String address = info.get("address").toString().trim();
        List<String> telelphoneNumbers = Arrays.asList(info.get("telelphoneNumbers").toString()
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .trim()
                .split(","));
        List<String> emailAddresses = Arrays.asList(info.get("emailAddresses").toString()
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .trim()
                .split(","));
        List<String> socialAcounts = Arrays.asList(info.get("socialAcounts").toString()
                .replace("[", "")
                .replace("]", "")
                .replace("\\", "")
                .replace("\"", "")
                .trim()
                .split(","));
        String profession = info.get("profession").toString().trim();
        String type = info.get("type").toString();

        String relation = (info.get("relation") != null)
                ? info.get("relation").toString().trim()
                : "";
        Contacts new_contact = null;

        switch (type) {
            case "Family":

                new_contact = new Family(name, lastname, type, relation, address, telelphoneNumbers,
                        emailAddresses, socialAcounts, profession);
                break;

            case "Friend":
                new_contact = new Friend(name, lastname, type, relation, address, telelphoneNumbers,
                        emailAddresses, socialAcounts, profession);
                break;
            case "Professional":
                new_contact = new Professional(name, lastname, type, relation, address, telelphoneNumbers,
                        emailAddresses, socialAcounts, profession);

                break;
            default:
                break;
        }
        addToContactList(new_contact);
    }

    public void loadDatabase() {

        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("db/contacts.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            var contactList = (JSONArray) obj;
            contactList.forEach(contact -> parseContact(contact));

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    public void addToContactList(Contacts newContact) {

        contactsList.add(newContact);
        // sort
        sortContactList();

    }

    public boolean delete(String searchKey) {
        Optional<Contacts> contact = contactsList
                .stream()
                .filter(e -> e.getName().equals(searchKey))
                .findFirst();
        if (contact.isPresent()) {
            int index = contactsList.indexOf(contact.get());
            contactsList.remove(index);
            return true;
        }
        return false;
    }

    public void sortContactList() {
        Comparator<Contacts> compareByName = Comparator
                .comparing(Contacts::getName)
                .thenComparing(contacts -> contacts.getLastname().get(0));

        Collections.sort(contactsList, compareByName);

    }

    public ObservableList<Contacts> getContactList() {
        return contactsList;
    }

    public void setContactList(ObservableList<Contacts> contactsList) {
        this.contactsList = contactsList;
    }
}
