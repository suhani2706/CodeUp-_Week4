/*This file is of the "Conversion of numbeer systems and operations". In this you can convert to any number system from any number system.
 * For example Converting from hex to binary, binary to decimal,etc.
 * This file contains main method.
 * Created On : 26 September 2024
 * Created By : Suhani Mathur
 */


import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        boolean action = true;

        while (action) {
            System.out.println(Constants.WELCOME);
            System.out.println(Constants.MENU);
            String input = userInput.next();

            switch (input) {
                case "1":
                    System.out.println(Constants.CONVO);
                    performConversion(userInput);
                    break;
                case "2":
                    System.out.println(Constants.OP);
                    performOperations(userInput);
                    break;
                case "3":
                    System.out.println(Constants.EXIT);
                    userInput.close();
                    return;
                default:
                    System.out.println(Constants.INVALID_CHOICE);
            }
        }
    }


    private static void performConversion(Scanner userInput) {
        boolean performConversion = true;
        while (performConversion) {
            ConvAndOp.conversions(userInput);
            performConversion = playAgain(userInput);
        }
    }


    private static void performOperations(Scanner userInput) {
        boolean performOperations = true;
        while (performOperations) {
            ConvAndOp.handleOperations(userInput);
            performOperations = playAgain(userInput);
        }
    }

    private static boolean playAgain(Scanner userInput) {
        System.out.println(Constants.PLAY_AGAIN);
        String response = userInput.next().toLowerCase();

        switch (response) {
            case "y":
            case "yes":
                return true;
            case "n":
            case "no":
                System.out.println(Constants.MAIN_MENU);
                return false;
            default:
                System.out.println(Constants.RESPONSE);
                return false;
        }
    }
}
