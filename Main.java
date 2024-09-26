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
            System.out.println("Hey User!!");
            System.out.println("What do you wanna perform?");
            System.out.println("1. Conversions");
            System.out.println("2. Operations");
            System.out.println("3. Exit");

            String input = userInput.next();

            switch (input) {
                case "1":
                    System.out.println("You chose Conversions!");
                    performConversion(userInput);
                    break;
                case "2":
                    System.out.println("You chose Operations!");
                    performOperations(userInput);
                    break;
                case "3":
                    System.out.println("Exiting... Goodbye!");
                    userInput.close();
                    return;
                default:
                    System.out.println("Invalid option! Please enter 1, 2, or 3.");
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
        System.out.println("Do you want to perform another action? (y/yes or n/no)");
        String response = userInput.next().toLowerCase();

        switch (response) {
            case "y":
            case "yes":
                return true;
            case "n":
            case "no":
                System.out.println("Returning to the main menu...");
                return false;
            default:
                System.out.println("Invalid input, returning to main menu.");
                return false;
        }
    }
}
