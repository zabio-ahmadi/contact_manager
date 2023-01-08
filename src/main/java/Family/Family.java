package Family;

import java.util.List;
import java.util.Scanner;

import Contacts.Contacts;
import Helper.Helper;

public class Family extends Contacts {
    private String Type;
    private String familyRelation;

    public Family() {
        super();
        this.Type = "Family";
    }

    public Family(String name, List<String> lastname, String address, List<String> telelphoneNumbers, List<String> emailAddresses,
                  List<String> socialAcounts, String profession, String familyRelation) {
        super(name, lastname, address, telelphoneNumbers, emailAddresses, socialAcounts, profession);
        Type = "Family";
        setFamilyRelation(familyRelation);
    }

    @Override
    public String getType() {
        return Type;
    }

    @Override
    public void setType(String type) {
        Type = type;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getFamilyRelation() {
        return this.familyRelation;
    }

    public void askFamilyRelation() {

        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter Your relation with contact owner [father, mother, brother, sister, ...] : ");
        String famRelation = sc.nextLine();

        while (famRelation.length() < 5) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT FAMILY RELATION !%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out.print(" Enter Your relation with contact owner [father, mother, brother, sister, ...] : ");
            famRelation = sc.nextLine();
        }
        setFamilyRelation(famRelation);

    }

    public Family askContact() {
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "CREATE A CONTACT  [Family]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        askName();
        askLastName();
        askEmail();
        //
        askFamilyRelation();
        askTelephoneNumber();
        askAdress();

        askSocialAcount();
        askProfession();
        return this;

    }
    public void updateContact(){
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "UPDATE CONTACT  [Family]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        System.out.println("CHOOSE WHICH FIELD YOU WANT TO UPDATE");
        System.out.println("[1] NAME ");
        System.out.println("[2] LASTNAME ");
        System.out.println("[3] EMAIL");
        System.out.println("[4] FAMILY RELATION:");
        System.out.println("[5] TELEPHONE NUMBER:");
        System.out.println("[6] ADDRESS:");
        System.out.println("[7] SOCIAL ACOUNT:");
        System.out.println("[8] PROFESSION:");

        System.out.format("%s %s %s : [] ", BLUE, "YOUR OPTION ", BLUE);
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        while(option < 1 || option > 8){
            System.out.format("%s %s %s\n", RED, "ERREUR: PLEASE CHOOSE THE CORRECT OPTION", RED);
            System.out.format("%s", RESET);
            System.out.format("%s %s %s : [] ", BLUE, "YOUR OPTION ", BLUE);
            option = sc.nextInt();
        }

        switch (option){
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
                askFamilyRelation();
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
        System.out.println(String.format(" %sFamily Relation  : %s", BLUE,
                BLUE)
                + String.format("\t\t%s" + getFamilyRelation(), RESET));

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
                + String.format("%s" + social_acounts, RESET));

        System.out.println();
        System.out.println(String.format(" %sProfession : %s", BLUE,
                BLUE)
                + String.format("\t\t\t%s" + getProfession(), RESET));

        System.out.println();
        System.out.println(
                "----------------------------------------------------------------------------------\n");

    }

}
