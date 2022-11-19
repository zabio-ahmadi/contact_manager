package Application;

import Contacts.Contacts;
import Family.Family;
import Friends.Friends;
import Helper.Helper;
import Professional.Professional;

import java.util.Objects;
import java.util.Scanner;

public class Application extends Helper {
    Contacts[] contact_list;

    public void addToContactList(Contacts newContact) {

        // if null
        if (contact_list == null) {
            contact_list = new Contacts[1];
            contact_list[0] = newContact;
        } else {
            Contacts[] temp = new Contacts[contact_list.length + 1];

            for (int i = 0; i < contact_list.length; i++) {
                temp[i] = contact_list[i];
            }
            temp[contact_list.length] = newContact;

            contact_list = temp;
            // bubble sort
            bubbleSort();
        }
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
        if (elementId >= 0 && elementId < contact_list.length) {
            contact_list[elementId].updateContact();
            return true;
        }
        return false;
    }

    public Contacts[] search() {

        Contacts[] result = new Contacts[0];
        Scanner scanner = new Scanner(System.in);
        System.out.println("CHOOSE BY WHICH FIELD YOU WANT TO SEARCH");
        System.out.println("[1] NAME ");
        System.out.println("[2] LASTNAME ");
        System.out.println("[3] EMAIL");
        System.out.println("[4] BY TYPE:");

        System.out.println(String.format("%s %s %s : []", BLUE, "YOUR FIELD ", BLUE));
        System.out.print(String.format("%s", RESET));
        int option = scanner.nextInt();
        while (option < 1 || option > 4) {
            System.out.println(String.format("%s %s %s", RED, "ERREUR: PLEASE CHOOSE THE CORRECT FIELD", RED));
            System.out.println(String.format("%s %s %s : [] ", BLUE, "YOUR FIELD ", BLUE));
            System.out.print(String.format("%s", RESET));
            option = scanner.nextInt();
        }

        if (option == 1) {

            System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A NAME TO SEARCH ", BLUE));
            System.out.print(String.format("%s", RESET));
            Scanner sc = new Scanner(System.in);
            String nameToSearch = sc.nextLine();
            while (nameToSearch.length() < 3) {
                System.out.println(
                        String.format("%s %s %s", RED, "NAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED));
                System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A NAME TO SEARCH ", BLUE));
                System.out.print(String.format("%s", RESET));
                nameToSearch = sc.nextLine();
            }

            int j = 0;
            for (int i = 0; i < contact_list.length; i++) {
                if (contact_list[i].getName().equals(nameToSearch)) {
                    result = new Contacts[result.length + 1];
                    result[j++] = contact_list[i];
                }
            }
        }

        // }
        if (option == 2)

        {
            int j = 0;
            System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A LASTNAME TO SEARCH ", BLUE));
            System.out.print(String.format("%s", RESET));
            Scanner sc = new Scanner(System.in);
            String lastNameToSerach = sc.nextLine();
            while (lastNameToSerach.length() < 3) {
                System.out.println(
                        String.format("%s %s %s", RED, "LASTNAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED));
                System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A LASTNAME TO SEARCH ", BLUE));
                System.out.print(String.format("%s", RESET));
                lastNameToSerach = sc.nextLine();
            }

            for (int i = 0; i < contact_list.length; i++) {
                if (contact_list[i].getLastname().equals(lastNameToSerach)) {
                    result = new Contacts[result.length + 1];
                    result[j++] = contact_list[i];
                }
            }
        } else if (option == 3) {
            int j = 0;
            System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A EMAIL TO SEARCH ", BLUE));
            System.out.print(String.format("%s", RESET));
            Scanner sc = new Scanner(System.in);
            String emailToSearch = sc.nextLine();
            while (emailToSearch.length() < 3) {
                System.out.println(
                        String.format("%s %s %s\n", RED, "LASTNAME SHOULD CONTAINS AT LEAST 3 CHARACTER LENGTH", RED));
                System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A EMAIL TO SEARCH", BLUE));
                System.out.print(String.format("%s", RESET));
                emailToSearch = sc.nextLine();
            }

            for (int i = 0; i < contact_list.length; i++) {
                if (contact_list[i].getEmailAddresses().equals(emailToSearch)) {
                    result = new Contacts[result.length + 1];
                    result[j++] = contact_list[i];
                }
            }
        } else if (option == 4) {
            int j = 0;
            System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A TYPE TO SEARCH ", BLUE));
            System.out.print(String.format("%s", RESET));
            Scanner sc = new Scanner(System.in);
            String typeToSearch = sc.nextLine();
            while (typeToSearch.length() < 5) {
                System.out.println(
                        String.format("%s %s %s\n", RED, "TYPE SHOULD CONTAINS AT LEAST 5 CHARACTER LENGTH", RED));
                System.out.println(String.format("%s %s %s : [] ", BLUE, "ENTER A TYPE TO SEARCH ", BLUE));
                System.out.print(String.format("%s", RESET));
                typeToSearch = sc.nextLine();
            }

            for (int i = 0; i < contact_list.length; i++) {
                if (contact_list[i].getType().equals(typeToSearch)) {
                    result = new Contacts[result.length + 1];
                    result[j++] = contact_list[i];
                }
            }
        }

        return result;
    }

    public boolean delete(int id) {

        int elementId = id - 1;
        if (elementId >= 0 && elementId < contact_list.length) {
            // delete
            Contacts[] temp = new Contacts[contact_list.length - 1];

            int j = 0;
            for (int i = 0; i < contact_list.length; i++) {
                if (i != elementId) {
                    temp[j] = contact_list[i];
                    j++;
                }
            }
            contact_list = temp;
            return true;

        } else {
            return false;
        }
    }

    public int getCharNumericValue(String value) {
        return (int) (value.charAt(0));
    }

    public void shift(int index) {
        Contacts tempContact = contact_list[index];
        contact_list[index] = contact_list[index - 1];
        contact_list[index - 1] = tempContact;
    }

    public void bubbleSort() {
        for (int i = 0; i < contact_list.length; i++) {

            for (int j = contact_list.length - 1; j >= 1; j--) {

                int firstPersonNameValue = getCharNumericValue(contact_list[j].getName().toUpperCase());
                int firstPersonLastNameValue = getCharNumericValue(contact_list[j].getLastname()[0].toUpperCase());

                int secondPersonNameValue = getCharNumericValue(contact_list[j - 1].getName().toUpperCase());
                int secondPersonLastNameValue = getCharNumericValue(contact_list[j - 1].getLastname()[0].toUpperCase());

                // sort by last name if first names are equals
                if (firstPersonNameValue == secondPersonNameValue) {

                    if (firstPersonLastNameValue < secondPersonLastNameValue) {
                        shift(j);
                    }
                    // sort by first name
                } else if (firstPersonNameValue < secondPersonNameValue) {
                    shift(j);
                }

            }
        }
    }

    public void showContacts() {
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", YELLOW);
        PrintInColor(String.format("SHOW CONTACTS \t\t\t\t\t\t\tTOTAL [%s %d %s]\n", WHITE,
                (contact_list != null) ? contact_list.length : 0, CYAN), CYAN);
        PrintInColor("==================================================================================\n\n", YELLOW);

        if (contact_list == null) {
            System.out.println(" No contacts yet !\n");
        }

        if (contact_list != null) {
            for (int i = 0; i < contact_list.length; i++) {

                contact_list[i].showContact();
            }
        }
    }

    public void run() {
        int option = ASK_USER_INPUT;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (option == ASK_USER_INPUT) {

                PrintHeader();
                PrintInColor("Enter your option : ", WHITE);

                option = scanner.nextInt();

                if (option == EXIT_CONSOLE) {
                    System.exit(0);
                }

                if (option == ADD_CONTACTS) {

                    addToContactList(createContact());
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

                    if (contact_list != null) {
                        Contacts[] result = search();
                        if (result.length != 0) {
                            for (int i = 0; i < result.length; i++) {

                                result[i].showContact();
                            }
                            printFooter();
                            option = scanner.nextInt();
                            option = populateErrorOrReturnHome(option);

                        } else {
                            System.out.println("---------------------------------------------------");
                            System.out.println(String.format("no contact found"));
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
                    if (contact_list != null) {

                        System.out.format("%s %s %s : [] ", BLUE, "ENTER A CONTACT ID TO UPDATE: ", BLUE);
                        int id = scanner.nextInt();

                        while (id < 1 || id > contact_list.length + 1) {
                            System.out.format("%s %s %s\n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT ID", RED);
                            System.out.format("%s %s %s : [] ", BLUE, "ENTER A CONTACT ID TO UPDATE: ", BLUE);
                            id = scanner.nextInt();
                        }

                        Boolean updated = update(id);
                        if (updated) {
                            option = ASK_USER_INPUT;
                        } else {
                            System.out.println(String.format(
                                    " Contact with id %d doesn't exists", id));
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

                    if (contact_list != null) {
                        deleteOption();
                        System.out.print("Enter contact id to delete: ");
                        int id = scanner.nextInt();

                        boolean deleted = delete(id);

                        if (deleted) {
                            option = ASK_USER_INPUT;
                        } else {
                            System.out.println(String.format(
                                    " Contact with id %d doesn't exists", id));
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
}
