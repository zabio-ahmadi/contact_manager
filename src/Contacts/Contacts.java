package Contacts;

import Config.Config;
import Helper.Helper;
import Person.Person;

import java.util.Scanner;

public class Contacts {

    static Person[] contact;

    public Contacts() {
    }

    public Contacts(Person[] persons) {
        contact = persons;
    }

    public static Contacts of(Person... persons) {
        return new Contacts(persons);
    }

    public Person[] getContacts() {
        return contact;
    }

    public void setContacts(Person[] person) {
        contact = person;
    }

    private int getCharNumericValue(String value) {
        return (int) (value.charAt(0));
    }

    private void shift(int index) {
        Person tempContact = contact[index];
        contact[index] = contact[index - 1];
        contact[index - 1] = tempContact;
    }

    private void bubbleSort() {

        for (int i = 0; i < contact.length; i++) {

            for (int j = contact.length - 1; j >= 1; j--) {

                int firstPersonNameValue = getCharNumericValue(contact[j].getName().toUpperCase());
                int firstPersonLastNameValue = getCharNumericValue(contact[j].getLastname()[0].toUpperCase());

                int secondPersonNameValue = getCharNumericValue(contact[j - 1].getName().toUpperCase());
                int secondPersonLastNameValue = getCharNumericValue(contact[j - 1].getLastname()[0].toUpperCase());

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

    public void add(Person newContact) {
        Person[] temp = new Person[contact.length + 1];

        for (int i = 0; i < contact.length; i++) {
            temp[i] = contact[i];
        }
        temp[contact.length] = newContact;

        contact = temp;
        // bubble sort
        bubbleSort();

    }

    public Person createOrupdate(String flag) {

        Helper.clearConsoleScreen();
        Helper.PrintInColor(" ==================================================================================\n",
                Config.GREEN);
        Helper.PrintInColor(String.format("%s\n", flag), Config.BLUE);
        Helper.PrintInColor("==================================================================================\n",
                Config.GREEN);

        Person person = new Person();
        Scanner sc = new Scanner(System.in);

        System.out.print(" Enter a name [] : ");
        String name = sc.nextLine();
        while (name.length() < 3) {
            System.out
                    .println(
                            String.format("\n %sName should contain at least 3 character %s%s",
                                    Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter a name [] : ");
            name = sc.nextLine();
        }
        person.setName(name);

        System.out.print(" Enter one or more last name separated by , [] : ");
        String lastName = sc.nextLine();

        while (lastName.length() < 3) {
            System.out
                    .println(
                            String.format("\n %slast name should contain at least 3 character %s%s",
                                    Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter one or more last name separated by , [] : ");
            name = sc.nextLine();
        }
        person.setLastname(lastName.split(","));

        System.out.print(" Enter Address [] : ");
        String address = sc.nextLine();
        while (address.length() < 15) {
            System.out
                    .println(
                            String.format("\n %sAddress should at least contain 15 character length%s%s",
                                    Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter Address [] : ");
            address = sc.nextLine();
        }
        person.setAddress(address);

        System.out.print(" Enter one or more email separated by , [] : ");
        String email = sc.nextLine();

        while (email.length() < 10 || !email.contains("@") || !email.contains(".")) {
            System.out
                    .println(
                            String.format("\n %sPlease Insert a correct Email !%s%s", Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter one or more email separated by , [] : ");

            email = sc.nextLine();
        }
        person.setEmailAddresses(email.split(","));

        System.out.print(" Enter one or more telephone number separated by , [] : ");
        String telephonNumber = sc.nextLine();

        while (telephonNumber.length() < 12 || !telephonNumber.contains("+")
                || telephonNumber.replaceAll(" ", telephonNumber).length() < 12) { // +4178 223 22 44
            System.out.println(
                    String.format("\n %sPLEASE INSERT A CORRECT TELEPHONE NUMBER !%s%s",
                            Config.RED,
                            Config.RED,
                            Config.RESET));
            System.out.println();
            System.out.print(" Enter one or more telephone number separated by , [] : ");
            email = sc.nextLine();

        }
        person.setTelelphoneNumbers(telephonNumber.split(","));

        System.out.print(" Enter one or more social acount URL separated by , [] :");
        String socialAcount = sc.nextLine();

        while (!socialAcount.contains("https://")) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT URL PREFIXED BY HTTPS:// !%s%s",
                                    Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter one or more social acount URL separated by , [] :");
            socialAcount = sc.nextLine();
        }
        person.setSocialAcounts(socialAcount.split(","));

        System.out.print(" Enter Your Contact Profession [] : ");
        String profession = sc.nextLine();

        while (profession.length() < 5) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT PROFESSION !%s%s",
                                    Config.RED,
                                    Config.RED,
                                    Config.RESET));
            System.out.println();
            System.out.print(" Enter Your Contact Profession [] : ");
            profession = sc.nextLine();
        }
        person.setProfession(profession);
        return person;
    }

    public boolean delete(int id) {

        int elementId = id - 1;
        if (elementId >= 0 && elementId < contact.length) {
            // delete
            Person[] temp = new Person[contact.length - 1];

            int j = 0;
            for (int i = 0; i < contact.length; i++) {
                if (i != elementId) {
                    temp[j] = contact[i];
                    j++;
                }
            }
            contact = temp;
            return true;

        } else {
            return false;
        }
    }

    public boolean update(int id) {

        int elementId = id - 1;
        if (elementId >= 0 && elementId < contact.length) {

            contact[elementId] = this.createOrupdate("UPDATE A CONTACT");
            return true;
        } else {
            return false;
        }
    }
}
