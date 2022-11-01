package Helper;

import Config.Config;
import Contacts.Contacts;
import Person.Person;
import java.util.Scanner;

public class Helper extends Config {

        // clear console screen
        public static void clearConsoleScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        // print in color a given text or change background color
        public static void PrintInColor(String text, String color) {

                System.out.format("%s %s %s", color, text, color);
                System.out.format("%s", RESET);
        }

        // Print header of our console application
        public static void PrintHeader() {

                clearConsoleScreen();
                PrintInColor(" ==================================================================================\n",
                                YELLOW);
                PrintInColor("WELLCOME TO CONTACT MANAGEMENT SYSTEM \n", GREEN);
                PrintInColor("==================================================================================\n",
                                YELLOW);

                PrintInColor("[1]: \tshow contacts\t\t\t\t\n", CYAN);
                PrintInColor("[2]: \tadd contact\t\t\t\t\n", CYAN);
                PrintInColor("[3]: \tupdate a contact\t\t\t\t\n", CYAN);
                PrintInColor("[4]: \tdelete a contact\t\t\t\t\n", CYAN);
                PrintInColor("[5]: \texit\t\t\t\t\n", CYAN);
                PrintInColor("==================================================================================\n",
                                YELLOW);
        }

        // print OPTION ERROR
        public static void PrintErrorAndReturn() {

                PrintInColor(" ----------------------------------------------------------------------------------\n",
                                YELLOW);
                PrintInColor("ERROR: WRONG OPTION ID \n", RED);
                PrintInColor("----------------------------------------------------------------------------------\n",
                                YELLOW);

                PrintInColor("[1]: \treturn to main menu\t\t\t\t\n", CYAN);
                PrintInColor("[2]: \texit\t\t\t\t\n", CYAN);
                PrintInColor("----------------------------------------------------------------------------------\n",
                                YELLOW);

        }

        public static void printFooter() {

                PrintInColor(" ----------------------------------------------------------------------------------\n",
                                YELLOW);
                PrintInColor("RETURN TO HOME \n", CYAN);
                PrintInColor("----------------------------------------------------------------------------------\n",
                                YELLOW);

                PrintInColor("[1]: \treturn to main menu\t\t\t\t\n", CYAN);
                PrintInColor("[2]: \texit\t\t\t\t\n", CYAN);
                PrintInColor("----------------------------------------------------------------------------------\n",
                                YELLOW);
        }

        public static void deleteOption() {
                PrintInColor(" ==================================================================================\n",
                                YELLOW);
                PrintInColor("DELETE A CONCTACT\n", CYAN);
                PrintInColor("==================================================================================\n",
                                YELLOW);
        }

        public static void showContacts(Person[] person) {

                clearConsoleScreen();
                PrintInColor(" ==================================================================================\n",
                                YELLOW);
                PrintInColor(String.format("SHOW CONTACTS \t\t\t\t\t\t\tTOTAL [%s %d %s]\n", WHITE,
                                (person != null) ? person.length : 0,
                                CYAN), CYAN);
                PrintInColor("==================================================================================\n\n",
                                YELLOW);

                if (person == null) {
                        System.out.println(" No contacts yet !\n");

                }
                if (person != null) {

                        for (int i = 0; i < person.length && person[i] != null; i++) {

                                System.out.println(
                                                "----------------------------------------------------------------------------------");
                                System.out.println();

                                System.out.println(String.format(" %sId : %s", BLUE, BLUE)
                                                + String.format("\t\t\t\t%s" + "[ " + (i + 1) + " ]", RESET));
                                System.out.println();

                                System.out.println(String.format(" %sName : %s", BLUE, BLUE)
                                                + String.format("\t\t\t%s" + person[i].getName(), RESET));

                                System.out.println();
                                String lastnames = String.join(",", person[i].getLastname());
                                System.out.println(String.format(" %sLast Name : %s", BLUE,
                                                BLUE)
                                                + String.format("\t\t\t%s" + lastnames, RESET));

                                System.out.println();
                                System.out.println(String.format(" %sAddress : %s", BLUE, BLUE)
                                                + String.format("\t\t\t%s" + person[i].getAddress(), RESET));

                                System.out.println();
                                String emails = String.join(", ", person[i].getEmailAddresses());
                                System.out.println(String.format(" %sEmail : %s", BLUE, BLUE)
                                                + String.format("\t\t\t%s" + emails, RESET));

                                System.out.println();
                                String telephone_numbers = String.join(", ",
                                                person[i].getTelelphoneNumbers());
                                System.out.println(String.format(" %sTelephone Number : %s", BLUE,
                                                BLUE)
                                                + String.format("\t\t%s" + telephone_numbers, RESET));

                                System.out.println();
                                String social_acounts = String.join(", ", person[i].getSocialAcounts());
                                System.out.println(String.format(" %sSocial Acount : %s", BLUE,
                                                BLUE)
                                                + String.format("\t\t%s" + social_acounts, RESET));

                                System.out.println();
                                System.out.println(String.format(" %sProfession : %s", BLUE,
                                                BLUE)
                                                + String.format("\t\t\t%s" + person[i].getProfession(), RESET));

                                System.out.println();
                                System.out.println(
                                                "----------------------------------------------------------------------------------\n");
                        }
                }

        }

        public static int populateErrorOrReturnHome(int option) {
                if (option == 1) {
                        return ASK_USER_INPUT;
                }
                if (option == 2) {
                        System.exit(0);
                }
                return 0;
        }

        public static void runApplication() {

                int option = ASK_USER_INPUT;

                Contacts contacts = new Contacts();
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

                                        Person[] newContact = { contacts.createOrupdate("Add A NEW CONTACT") };
                                        if (contacts.getContacts() == null) {
                                                contacts = new Contacts(newContact);
                                        } else {
                                                contacts.add(newContact[0]);
                                        }
                                        option = ASK_USER_INPUT;
                                        continue;
                                }

                                if (option == SHOW_CONTACTS) {

                                        showContacts(contacts.getContacts());
                                        printFooter();

                                        PrintInColor("Enter your option : ", WHITE);
                                        option = scanner.nextInt();
                                        option = populateErrorOrReturnHome(option);
                                        continue;

                                }

                                // update contact
                                if (option == UPDATE_CONTACTS) {
                                        showContacts(contacts.getContacts());

                                        if (contacts.getContacts() != null) {

                                                System.out.print("Enter a contact id to update : ");
                                                int id = scanner.nextInt();

                                                boolean updated = contacts.update(id);

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
                                                showContacts(contacts.getContacts());
                                                printFooter();
                                                option = scanner.nextInt();
                                                option = populateErrorOrReturnHome(option);
                                        }
                                        continue;
                                }
                                // delete contact
                                if (option == DELETE_CONTACT) {
                                        showContacts(contacts.getContacts());

                                        if (contacts.getContacts() != null) {
                                                deleteOption();
                                                System.out.print("Enter contact id to delete: ");
                                                int id = scanner.nextInt();

                                                boolean deleted = contacts.delete(id);

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
