package Friends;

import Contacts.Contacts;
import java.util.List;
import java.util.Scanner;

public class Friends extends Contacts {

    private String Type;

    private String firendsSince;

    public Friends() {
        super();
        Type = "Friend";
    }

    public Friends(String name, List<String> lastname, String address, List<String> telelphoneNumbers,
            List<String> emailAddresses,
            List<String> socialAcounts, String profession, String firendsSince) {
        super(name, lastname, address, telelphoneNumbers, emailAddresses, socialAcounts, profession);
        Type = "Friend";
        setFriendsSince(firendsSince);
    }

    @Override
    public String getType() {
        return Type;
    }

    @Override
    public void setType(String type) {
        Type = type;
    }

    public void setFriendsSince(String str) {
        this.firendsSince = str;

    }

    public String getFriendsSince() {
        return this.firendsSince;
    }

    public void askFriendShipDate() {

        Scanner sc = new Scanner(System.in);
        System.out.print(this.name + "  is Your friend since : ");
        String friendshipeDate = sc.nextLine();

        while (friendshipeDate.length() < 5) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT FRIENDSHIP DATE !%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(this.name + " is Your friend since : ");
            friendshipeDate = sc.nextLine();
        }
        setFriendsSince(friendshipeDate);
    }

    public Contacts askContact() {

        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "CREATE A CONTACT [Friends]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        askName();
        askLastName();
        askEmail();
        //
        askFriendShipDate();
        askTelephoneNumber();
        askAdress();
        askSocialAcount();
        askProfession();
        return this;

    }

    public void updateContact() {
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "UPDATE CONTACT  [FRIENDS]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        System.out.println("CHOOSE WHICH FIELD YOU WANT TO UPDATE");
        System.out.println("[1] NAME ");
        System.out.println("[2] LASTNAME ");
        System.out.println("[3] EMAIL");
        System.out.println("[4] FRIENDSHIP DATE:");
        System.out.println("[5] TELEPHONE NUMBER:");
        System.out.println("[6] ADDRESS:");
        System.out.println("[7] SOCIAL ACOUNT:");
        System.out.println("[8] PROFESSION:");

        System.out.format("%s %s %s : [] ", BLUE, "YOUR OPTION ", BLUE);
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        while (option < 1 || option > 8) {
            System.out.format("%s %s %s\n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT OPTION", RED);
            System.out.format("%s", RESET);
            System.out.format("%s %s %s : [] ", BLUE, "YOUR OPTION ", BLUE);
            option = sc.nextInt();
        }

        switch (option) {
            case 1:
                askName();
                break;
            case 2:
                askLastName();
                break;
            case 3:
                askEmail();
                break;
            case 4:
                askFriendShipDate();
                break;
            case 5:
                askTelephoneNumber();
                break;
            case 6:
                askAdress();
                break;
            case 7:
                askSocialAcount();
                break;
            case 8:
                askProfession();
                break;
        }
    }

    @Override
    public void showContact() {

        System.out.println(
                "----------------------------------------------------------------------------------");
        System.out.println();

        System.out.println(String.format(" %sName : %s", BLUE, BLUE)
                + String.format("\t\t\t%s" + getName(), RESET));

        System.out.println();
        String lastnames = String.join(",", getLastname());
        System.out.println(String.format(" %sLast Name : %s", BLUE,
                BLUE)
                + String.format("\t\t\t%s" + lastnames, RESET));

        System.out.println();
        System.out.println(String.format(" %sFriendship since  : %s", BLUE,
                BLUE)
                + String.format("\t\t%s" + getFriendsSince(), RESET));

        System.out.println();
        System.out.println(String.format(" %sAddress : %s", BLUE, BLUE)
                + String.format("\t\t\t%s" + getAddress(), RESET));

        System.out.println();
        String emails = String.join(", ", getEmailAddresses());
        System.out.println(String.format(" %sEmail : %s", BLUE, BLUE)
                + String.format("\t\t\t%s" + emails, RESET));

        System.out.println();
        String telephone_numbers = String.join(", ", getTelelphoneNumbers());
        System.out.println(String.format(" %sTelephone Number : %s", BLUE,
                BLUE)
                + String.format("\t\t%s" + telephone_numbers, RESET));

        System.out.println();
        String social_acounts = String.join(", ", getSocialAcounts());
        System.out.println(String.format(" %sSocial Acount : %s", BLUE,
                BLUE)
                + String.format("\t\t%s" + social_acounts, RESET));

        System.out.println();
        System.out.println(String.format(" %sProfession : %s", BLUE,
                BLUE)
                + String.format("\t\t\t%s" + getProfession(), RESET));

        System.out.println();
        System.out.println(
                "----------------------------------------------------------------------------------\n");

    }
}
