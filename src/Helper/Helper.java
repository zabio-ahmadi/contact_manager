package Helper;



public abstract class Helper {

    // text color
    protected  final String RED = "\u001B[31m";
    protected  final String BLACK = "\u001B[30m";
    protected  final String GREEN = "\u001B[32m";
    protected  final String BLUE = "\u001B[34m";
    protected  final String RESET = "\u001B[0m";
    protected  final String PURPLE = "\u001B[35m";
    protected  final String CYAN = "\u001B[36m";
    protected  final String YELLOW = "\u001B[33m";
    protected  final String WHITE = "\u001B[37m";

    protected  final String YELLOW_BACKGROUND = "\u001B[43m";
    protected  final String BLUE_BACKGROUND = "\u001B[44m";
    protected  final String BLACK_BACKGROUND = "\u001B[40m";
    protected  final String PURPLE_BACKGROUND = "\u001B[45m";
    protected  final String CYAN_BACKGROUND = "\u001B[46m";
    protected  final String GREEN_BACKGROUND = "\u001B[42m";
    protected  final String WHITE_BACKGROUND = "\u001B[47m";

    protected  final int ASK_USER_INPUT = 110;
    protected  final int SHOW_CONTACTS = 1;
    protected  final int ADD_CONTACTS = 2;
    protected  final int SEARCH_CONTACT = 3;
    protected  final int UPDATE_CONTACTS = 4;
    protected  final int DELETE_CONTACT = 5;
    protected  final int EXIT_CONSOLE = 6;

    // clear console screen
    protected void clearConsoleScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // print in color a given text or change background color
    protected void PrintInColor(String text, String color) {

        System.out.format("%s %s %s", color, text, color);
        System.out.format("%s", RESET);
    }

    // Print header of our console application
    protected void PrintHeader() {

        clearConsoleScreen();
        PrintInColor(" ==================================================================================\n",
                YELLOW);
        PrintInColor("WELLCOME TO CONTACT MANAGEMENT SYSTEM \n", GREEN);
        PrintInColor("==================================================================================\n",
                YELLOW);

        PrintInColor("[1]: \tshow contacts\t\t\t\t\n", CYAN);
        PrintInColor("[2]: \tadd contact\t\t\t\t\n", CYAN);
        PrintInColor("[3]: \tsearch contact\t\t\t\t\n", CYAN);
        PrintInColor("[4]: \tupdate a contact\t\t\t\t\n", CYAN);
        PrintInColor("[5]: \tdelete a contact\t\t\t\t\n", CYAN);
        PrintInColor("[6]: \texit\t\t\t\t\n", CYAN);
        PrintInColor("==================================================================================\n",
                YELLOW);
    }

    // print OPTION ERROR
    protected  void PrintErrorAndReturn() {

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

    protected  void printFooter() {

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

    protected  void deleteOption() {
        PrintInColor(" ==================================================================================\n",
                YELLOW);
        PrintInColor("DELETE A CONCTACT\n", CYAN);
        PrintInColor("==================================================================================\n",
                YELLOW);
    }

    protected int populateErrorOrReturnHome(int option) {
        if (option == 1) {
            return ASK_USER_INPUT;
        }
        if (option == 2) {
            System.exit(0);
        }
        return 0;
    }

}
