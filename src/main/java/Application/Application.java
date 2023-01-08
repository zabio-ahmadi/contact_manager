package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Consumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import Contacts.Contacts;
import Family.Family;
import Friends.Friends;
import Helper.Helper;
import Professional.Professional;

public class Application extends Helper {
    List<Contacts> contactsList = new ArrayList<>();

    public void saveContactList(List<Contacts> contactsList) {

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
        Contacts new_contact = null;
        String type = info.get("type").toString();
        switch (type) {
            case "Family":

                String familyRelation = (info.get("familyRelation") != null)
                        ? info.get("familyRelation").toString().trim()
                        : "";
                new_contact = new Family(name, lastname, address, telelphoneNumbers, emailAddresses,
                        socialAcounts, profession, familyRelation);
                break;

            case "Friend":
                String friendsSince = (info.get("friendsSince") != null) ? info.get("friendsSince").toString().trim()
                        : "";
                new_contact = new Friends(name, lastname, address, telelphoneNumbers, emailAddresses,
                        socialAcounts, profession, friendsSince);
                break;
            case "Professional":
                String ProfessionRelation = (info.get("ProfessionRelation") != null)
                        ? info.get("ProfessionRelation").toString().trim()
                        : "";
                new_contact = new Friends(name, lastname, address, telelphoneNumbers, emailAddresses,
                        socialAcounts, profession, ProfessionRelation);
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

    public Contacts createContact() {

        System.out.println("Enter the type of contact: []");
        System.out.println("[1] for friend ");
        System.out.println("[2] for family ");
        System.out.println("[3] for professional");
        System.out.println("Enter your choice [] :");

        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        while (type < 1 || type > 3) {

            System.out.format("%s %s %s\n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT TYPE", RED);
            System.out.format("%s", RESET);
            System.out.println("Enter your choice [] :");
            type = scanner.nextInt();
        }

        Contacts new_contact = null;
        if (type == 1) {
            new_contact = new Friends()
                    .askContact();
        }

        else if (type == 2) {
            new_contact = new Family().askContact();
        }

        else if (type == 3) {
            new_contact = new Professional()
                    .askContact();
        } else {
            PrintErrorAndReturn();
        }
        // scanner.close();
        return new_contact;

    }

    public boolean update(int id) {

        int elementId = id - 1;
        if (!contactsList.isEmpty() && contactsList.get(elementId) != null) {
            contactsList.get(elementId).updateContact();
            sortContactList();
            return true;
        }
        return false;
    }

    public List<Contacts> search() {

        List<Contacts> result = new ArrayList<Contacts>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("CHOOSE BY WHICH FIELD YOU WANT TO SEARCH");
        System.out.println("[1] NAME ");
        System.out.println("[2] LASTNAME ");
        System.out.println("[3] EMAIL");
        System.out.println("[4] BY TYPE:");

        System.out.printf("%s %s %s : []%n", BLUE, "YOUR OPTION ", BLUE);
        System.out.printf("%s", RESET);
        int option = scanner.nextInt();
        while (option < 1 || option > 4) {
            System.out.printf("%s %s %s%n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT OPTION", RED);
            System.out.printf("%s %s %s : [] %n", BLUE, "YOUR OPTION ", BLUE);
            System.out.printf("%s", RESET);
            option = scanner.nextInt();
        }

        if (option == 1) {

            System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A NAME TO SEARCH ", BLUE);
            System.out.printf("%s", RESET);
            Scanner sc = new Scanner(System.in);
            String nameToSearch = sc.nextLine();
            while (nameToSearch.length() < 3) {
                System.out.printf("%s %s %s%n", RED, "NAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED);
                System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A NAME TO SEARCH ", BLUE);
                System.out.printf("%s", RESET);
                nameToSearch = sc.nextLine();
            }

            final String searchKey = nameToSearch;
            result = contactsList.stream().filter(e -> e.getName().contains(searchKey)).toList();
        }
        if (option == 2)

        {
            int j = 0;
            System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A LASTNAME TO SEARCH ", BLUE);
            System.out.printf("%s", RESET);
            Scanner sc = new Scanner(System.in);
            String lastNameToSerach = sc.nextLine();
            while (lastNameToSerach.length() < 3) {
                System.out.printf("%s %s %s%n", RED, "LASTNAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED);
                System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A LASTNAME TO SEARCH ", BLUE);
                System.out.printf("%s", RESET);
                lastNameToSerach = sc.nextLine();
            }

            final String searchKey = lastNameToSerach;
            result = contactsList.stream().filter(e -> e.getLastname().contains(searchKey)).toList();

        } else if (option == 3) {
            int j = 0;
            System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A EMAIL TO SEARCH ", BLUE);
            System.out.printf("%s", RESET);
            Scanner sc = new Scanner(System.in);
            String emailToSearch = sc.nextLine();
            while (emailToSearch.length() < 3) {
                System.out.printf("%s %s %s\n%n", RED, "LASTNAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED);
                System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A EMAIL TO SEARCH", BLUE);
                System.out.printf("%s", RESET);
                emailToSearch = sc.nextLine();
            }

            final String searchKey = emailToSearch;
            result = contactsList.stream().filter(e -> e.getEmailAddresses().contains(searchKey)).toList();

        } else if (option == 4) {
            int j = 0;
            System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A TYPE TO SEARCH ", BLUE);
            System.out.printf("%s", RESET);
            Scanner sc = new Scanner(System.in);
            String typeToSearch = sc.nextLine();
            while (typeToSearch.length() < 5) {
                System.out.printf("%s %s %s\n%n", RED, "TYPE SHOULD CONTAINS AT LEAST 5 CHARACTER LENGTH", RED);
                System.out.printf("%s %s %s : [] %n", BLUE, "ENTER A TYPE TO SEARCH ", BLUE);
                System.out.printf("%s", RESET);
                typeToSearch = sc.nextLine();
            }

            final String searchKey = typeToSearch;
            result = contactsList.stream().filter(e -> e.getType().contains(searchKey)).toList();
        }

        return result;
    }

    public boolean delete(int id) {

        int elementId = id - 1;

        if (elementId >= 0 && elementId < contactsList.size()) {
            // delete
            contactsList.remove(elementId);
            // sort
            sortContactList();
            return true;

        } else {
            return false;
        }
    }

    public void sortContactList() {
        Comparator<Contacts> compareByName = Comparator
                .comparing(Contacts::getName)
                .thenComparing(contacts -> contacts.getLastname().get(0));

        Collections.sort(contactsList, compareByName);

    }

    public void showContacts() {
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", YELLOW);
        PrintInColor(String.format("SHOW CONTACTS \t\t\t\t\t\t\tTOTAL [%s %d %s]\n", WHITE,
                (!contactsList.isEmpty()) ? contactsList.size() : 0, CYAN), CYAN);
        PrintInColor("==================================================================================\n\n", YELLOW);

        if (contactsList.isEmpty()) {
            System.out.println(" No contacts yet !\n");
        } else {
            contactsList.forEach(e -> e.showContact());
        }
    }

    public void run() {

        loadDatabase();
        sortContactList();
        int option = ASK_USER_INPUT;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (option == ASK_USER_INPUT) {

                PrintHeader();
                PrintInColor("Enter your option : ", WHITE);

                option = scanner.nextInt();

                if (option == EXIT_CONSOLE) {

                    saveContactList(contactsList); // save
                    System.exit(0);
                }

                if (option == ADD_CONTACTS) {

                    addToContactList(createContact());
                    saveContactList(contactsList); // save
                    option = ASK_USER_INPUT;
                    continue;
                }

                if (option == SHOW_CONTACTS) {
                    showContacts();
                    printFooter();

                    PrintInColor("Enter your option : ", WHITE);
                    option = scanner.nextInt();
                    option = populateErrorOrReturnHome(option);
                    continue;

                }

                if (option == SEARCH_CONTACT) {

                    if (!contactsList.isEmpty()) {
                        List<Contacts> result = search();
                        if (!result.isEmpty()) {

                            result.forEach(res -> res.showContact());
                            printFooter();
                            option = scanner.nextInt();
                            option = populateErrorOrReturnHome(option);

                        } else {
                            System.out.println("---------------------------------------------------");
                            System.out.println("no contact found");
                            System.out.println("---------------------------------------------------");
                            printFooter();
                            option = scanner.nextInt();
                            option = populateErrorOrReturnHome(option);
                        }
                    } else {
                        showContacts();
                        printFooter();
                        option = scanner.nextInt();
                        option = populateErrorOrReturnHome(option);

                    }
                    continue;
                }

                // update contact
                if (option == UPDATE_CONTACTS) {

                    showContacts();
                    if (!contactsList.isEmpty()) {

                        System.out.format("%s %s %s : [] ", BLUE, "ENTER A CONTACT ID TO UPDATE: ", BLUE);
                        int id = scanner.nextInt();

                        while (id < 1 || id > contactsList.size() + 1) {
                            System.out.format("%s %s %s\n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT ID", RED);
                            System.out.format("%s %s %s : [] ", BLUE, "ENTER A CONTACT ID TO UPDATE: ", BLUE);
                            id = scanner.nextInt();
                        }

                        Boolean updated = update(id);
                        if (updated) {

                            // save to database
                            saveContactList(contactsList);
                            option = ASK_USER_INPUT;
                        } else {
                            System.out.printf(
                                    " Contact with id %d doesn't exists%n", id);
                            PrintErrorAndReturn();
                            option = scanner.nextInt();
                            option = populateErrorOrReturnHome(option);
                        }

                    } else {
                        showContacts();
                        printFooter();
                        option = scanner.nextInt();
                        option = populateErrorOrReturnHome(option);
                    }
                    continue;
                }
                // delete contact
                if (option == DELETE_CONTACT) {
                    showContacts();

                    if (!contactsList.isEmpty()) {
                        deleteOption();
                        System.out.print("Enter contact id to delete: ");
                        int id = scanner.nextInt();

                        boolean deleted = delete(id);

                        if (deleted) {
                            option = ASK_USER_INPUT;
                            // save to database
                            saveContactList(contactsList);
                        } else {
                            System.out.printf(
                                    " Contact with id %d doesn't exists%n", id);
                            PrintErrorAndReturn();
                            option = scanner.nextInt();
                            option = populateErrorOrReturnHome(option);
                        }

                    } else {

                        printFooter();
                        option = scanner.nextInt();
                        option = populateErrorOrReturnHome(option);
                    }
                    continue;
                }

            }

            if (option < 1 || option > 4) {

                clearConsoleScreen();
                PrintErrorAndReturn();
                option = scanner.nextInt();
                option = populateErrorOrReturnHome(option);
            }
            scanner.close();// close scanner
        }
    }

    public List<Contacts> getContactList() {
        return this.contactsList;
    }
}
