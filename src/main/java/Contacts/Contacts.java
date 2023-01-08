package Contacts;

import Helper.Helper;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Contacts extends Helper {

    protected String name;
    protected List<String> lastname;
    protected String address;
    protected List<String> telelphoneNumbers;
    protected List<String> emailAddresses;
    protected List<String> socialAcounts;
    protected String profession;

    public Contacts() {
    }

    public Contacts(String name, List<String> lastname, String address, List<String> telelphoneNumbers,
            List<String> emailAddresses,
            List<String> socialAcounts, String profession) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.telelphoneNumbers = telelphoneNumbers;
        this.emailAddresses = emailAddresses;
        this.socialAcounts = socialAcounts;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public List<String> getLastname() {
        return lastname;
    }

    public void setLastname(List<String> lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTelelphoneNumbers() {
        return telelphoneNumbers;
    }

    public void setTelelphoneNumbers(List<String> telelphoneNumbers) {
        this.telelphoneNumbers = telelphoneNumbers;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public List<String> getSocialAcounts() {
        return socialAcounts;
    }

    public void setSocialAcounts(List<String> socialAcounts) {
        this.socialAcounts = socialAcounts;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void askName() {

        Scanner sc = new Scanner(System.in);

        System.out.print(" Enter a name [] : ");
        String name = sc.nextLine();
        while (name.length() < 3) {
            System.out
                    .println(
                            String.format("\n %sName should contain at least 3 character %s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter a name [] : ");
            name = sc.nextLine();
        }
        setName(name);
        // sc.close();
    }

    public void askLastName() {

        Scanner sc = new Scanner(System.in);

        System.out.print(" Enter one or more last name separated by , [] : ");
        String lastName = sc.nextLine();

        // validate
        while (lastName.length() < 3) {
            System.out
                    .println(
                            String.format("\n %slast name should contain at least 3 character %s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter one or more last name separated by , [] : ");
            name = sc.nextLine();
        }
        setLastname(Arrays.asList(lastName.split(",")));

        // sc.close();
    }

    public void askAdress() {
        Scanner sc = new Scanner(System.in);

        System.out.print(" Enter Address [] : ");
        String address = sc.nextLine();
        while (address.length() < 15) {
            System.out
                    .println(
                            String.format("\n %sAddress should at least contain 15 character length%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter Address [] : ");
            address = sc.nextLine();
        }
        setAddress(address);

        // sc.close();
    }

    public void askEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter one or more email separated by , [] : ");
        String email = sc.nextLine();

        while (email.length() < 10 || !email.contains("@") || !email.contains(".")) {
            System.out
                    .println(
                            String.format("\n %sPlease Insert a correct Email !%s%s", RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter one or more email separated by , [] : ");

            email = sc.nextLine();
        }
        setEmailAddresses(Arrays.asList(email.split(",")));
        // sc.close();
    }

    public void askTelephoneNumber() {
        Scanner sc = new Scanner(System.in);

        System.out.print(" Enter one or more telephone number separated by , [] : ");
        String telephonNumber = sc.nextLine();

        while (telephonNumber.length() < 12 || !telephonNumber.contains("+")
                || telephonNumber.replaceAll(" ", telephonNumber).length() < 12) { // +4178 223 22 44
            System.out.println(
                    String.format("\n %sPLEASE INSERT A CORRECT TELEPHONE NUMBER !%s%s",
                            RED,
                            RED,
                            RESET));
            System.out.println();
            System.out.print(" Enter one or more telephone number separated by , [] : ");
            telephonNumber = sc.nextLine();

        }
        setTelelphoneNumbers(Arrays.asList(telephonNumber.split(",")));
    }

    public void askSocialAcount() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter one or more social acount URL separated by , [] :");
        String socialAcount = sc.nextLine();

        while (!socialAcount.contains("https://")) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT URL PREFIXED BY HTTPS:// !%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter one or more social acount URL separated by , [] :");
            socialAcount = sc.nextLine();
        }
        setSocialAcounts(Arrays.asList(socialAcount.split(",")));
    }

    public void askProfession() {

        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter Your Contact Profession [] : ");
        String profession = sc.nextLine();

        while (profession.length() < 5) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT PROFESSION !%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter Your Contact Profession [] : ");
            profession = sc.nextLine();
        }
        setProfession(profession);
    }

    public void displayRequestHeader() {

        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "CREATE A CONTACT"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

    }

    public abstract String getType();

    public abstract void setType(String type);

    public abstract void showContact();

    public abstract void updateContact();

}
