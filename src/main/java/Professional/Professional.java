package Professional;

import java.util.List;
import java.util.Scanner;

import Contacts.Contacts;
import Family.Family;

public class Professional extends Contacts {
    private String Type;

    private String ProfessionRelation;

    public Professional() {
        super();
        Type = "Professional";
    }

    public Professional(String name, List<String> lastname, String address, List<String> telelphoneNumbers,
                        List<String> emailAddresses, List<String> socialAcounts, String profession, String professionRelation) {
        super(name, lastname, address, telelphoneNumbers, emailAddresses, socialAcounts, profession);
        setProfessionRelation(professionRelation);
        Type = "Professional";
    }

    @Override
    public String getType() {
        return Type;
    }

    @Override
    public void setType(String type) {
        Type = type;
    }

    public void setProfessionRelation(String professionRelation) {
        ProfessionRelation = professionRelation;
    }

    public String getProfessionalRelation() {
        return this.ProfessionRelation;
    }

    public void askProfessionalRelation() {

        Scanner sc = new Scanner(System.in);
        System.out.print(" Enter Your relation with contact owner [Boss, Collegue, Aprentice,collaborator, ...] : ");
        String proRelation = sc.nextLine();

        while (proRelation.length() < 5) {
            System.out
                    .println(
                            String.format("\n %sPLEASE INSERT A CORRECT PROFESSIONAL RELATION !%s%s",
                                    RED,
                                    RED,
                                    RESET));
            System.out.println();
            System.out
                    .print(" Enter Your relation with contact owner [Boss, Collegue, Aprentice,collaborator, ...] : ");
            proRelation = sc.nextLine();
        }
        setProfessionRelation(proRelation);

    }

    public Professional askContact() {
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "CREATE A CONTACT [Professional]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        askName();
        askLastName();
        askEmail();
        //
        askProfessionalRelation();
        askTelephoneNumber();
        askAdress();
        askSocialAcount();
        askProfession();

        return this;

    }

    public void updateContact(){
        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n", GREEN);
        PrintInColor(String.format("%s\n", "UPDATE CONTACT  [PROFESSIONAL]"), BLUE);
        PrintInColor("==================================================================================\n", GREEN);

        System.out.println("CHOOSE WHICH FIELD YOU WANT TO UPDATE");
        System.out.println("[1] NAME ");
        System.out.println("[2] LASTNAME ");
        System.out.println("[3] EMAIL");
        System.out.println("[4] PROFESSIONAL RELATION:");
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
                askProfessionalRelation();
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
        System.out.println(String.format(" %sProfessional Relation  : %s", BLUE,
                BLUE)
                + String.format("\t\t%s" + getProfessionalRelation(), RESET));

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
