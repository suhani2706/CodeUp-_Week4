/*This file is of the "Conversion of numbeer systems and operations". In this you can convert to any number system from any number system.
 * For example Converting from hex to binary, binary to decimal,etc.
 * This file contains functions of the program.
 * Created On : 26 September 2024
 * Created By : Suhani Mathur
 */
import java.util.Scanner;

public class ConvAndOp {

    //Enum for the operators + , - , * and /.
    public enum Operator {
        ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/');
        private char symbol;
        Operator(char symbol) {
            this.symbol = symbol;
        }
        public char getSymbol() {
            return symbol;
        }
        // Find operator by symbol for enums
        public static Operator getOperator(char symbol) {
            for (Operator op : Operator.values()) {
                if (op.symbol == symbol) {
                    return op;
                }
            }
            return null; // Return null if no valid operator found
        }
    }

    //Enum for Number System
    public enum NumberSystem {
        BINARY("binary"),
        OCTAL("octal"),
        DECIMAL("decimal"),
        HEXADECIMAL("hexadecimal");
        private String name;
        NumberSystem(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public static NumberSystem fromString(String str) {
            for (NumberSystem ns : NumberSystem.values()) {
                if (ns.name.equalsIgnoreCase(str)) {
                    return ns;
                }
            }
            return null; // Return null if no valid number system found
        }

        // Validate input based on the number system
        public boolean isValidInput(String input) {
            switch (this) {
                case BINARY:
                    return input.matches("[01]+"); // Only 0s and 1s
                case OCTAL:
                    return input.matches("[0-7]+"); // 0-7
                case DECIMAL:
                    return input.matches("\\d+"); // Only digits
                case HEXADECIMAL:
                    return input.matches("[0-9a-fA-F]+"); // 0-9 and a-f
                default:
                    return false;
            }
        }
    }

            // Handle user conversions
    public static void conversions(Scanner userInput) {
        System.out.println("Enter the number to convert:");
        String number = userInput.next();


        System.out.println("Which number system is this number from? (binary, decimal, octal, hexadecimal): ");

        String fromSystemStr1 = userInput.next().toLowerCase();
        NumberSystem fromSystem1 = NumberSystem.fromString(fromSystemStr1);

        if (fromSystem1 == null || !fromSystem1.isValidInput(number)) {
            System.out.println("Invalid input for the specified number system!");
            return;
        }

        System.out.println("Which system do you want to convert to? (binary, decimal, octal, hexadecimal): ");
        String toSystem = userInput.next().toLowerCase();

        // Conversion logic
        switch (fromSystemStr1) {
            case "binary":
            case "bi" :
                int decimalValueFromBinary = binaryToDecimal(number);
                convertToTargetSystem(decimalValueFromBinary, toSystem);
                break;
            case "decimal":
            case "dec" :
                int decimalValue = Integer.parseInt(number);
                convertToTargetSystem(decimalValue, toSystem);
                break;
            case "octal":
            case "oct" :
                int decimalValueFromOctal = octalToDecimal(number);
                convertToTargetSystem(decimalValueFromOctal, toSystem);
                break;
            case "hexadecimal":
            case "hexa" :
                 int decimalValueFromHex = hexToDecimal(number);
                convertToTargetSystem(decimalValueFromHex, toSystem);
                break;
            default:
                System.out.println("Invalid source number system!");
        }
    }


    // Convert input number to decimal based on its original system
    private static int convertToDecimal(String number, NumberSystem fromSystem, boolean showCalculations) {
        int decimalValue = -1;
        switch (fromSystem) {
            case BINARY:
                decimalValue = binaryToDecimal(number);
                if (showCalculations) {
                    System.out.println("Binary to Decimal Calculation: " + number + " = " + decimalValue);
                }
                break;
            case DECIMAL:
                decimalValue = Integer.parseInt(number);
                if (showCalculations) {
                    System.out.println("Decimal Value: " + decimalValue);
                }
                break;
            case OCTAL:
                decimalValue = octalToDecimal(number);
                if (showCalculations) {
                    System.out.println("Octal to Decimal Calculation: " + number + " = " + decimalValue);
                }
                break;
            case HEXADECIMAL:
                decimalValue = hexToDecimal(number);
                if (showCalculations) {
                    System.out.println("Hexadecimal to Decimal Calculation: " + number + " = " + decimalValue);
                }
                break;
            default:
                return -1; // Indicates an error
        }
        return decimalValue;
    }

    // Convert decimal to the target system based on user input
    public static void convertToTargetSystem(int decimalValue, String toSystem) {
        switch (toSystem) {
            case "binary":
                String binaryResult = decimalToBinary(decimalValue);
                System.out.println("Conversion result: " + binaryResult);

                break;
            case "octal":
                String octalResult = decimalToOctal(decimalValue);
                System.out.println("Conversion result: " + octalResult);

                break;
            case "hexadecimal":
                String hexResult = decimalToHex(decimalValue);
                System.out.println("Conversion result: " + hexResult);

                break;
            case "decimal":
                System.out.println("Conversion result: " + decimalValue);
                break;
            default:
                System.out.println("Invalid target system!");
        }
    }


    // Custom binary to decimal conversion logic
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int base = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2;
        }
        return decimal;
    }

    // Custom octal to decimal conversion logic
    public static int octalToDecimal(String octal) {
        int decimal = 0;
        int base = 1;
        for (int i = octal.length() - 1; i >= 0; i--) {
            decimal += (octal.charAt(i) - '0') * base;
            base *= 8;
        }
        return decimal;
    }

    // Custom hexadecimal to decimal conversion logic
    public static int hexToDecimal(String hex) {
        int decimal = 0;
        int base = 1;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char hexChar = hex.charAt(i);
            int hexValue;
            if (hexChar >= '0' && hexChar <= '9') {
                hexValue = hexChar - '0';
            } else {
                hexValue = hexChar - 'A' + 10;
            }
            decimal += hexValue * base;
            base *= 16;
        }
        return decimal;
    }

    // Custom decimal to binary conversion logic
    public static String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        return binary.length() == 0 ? "0" : binary;
    }

    // Custom decimal to octal conversion logic
    public static String decimalToOctal(int decimal) {
        String octal = "";
        while (decimal > 0) {
            octal = (decimal % 8) + octal;
            decimal /= 8;
        }
        return octal.length() == 0 ? "0" : octal;
    }

    // Custom decimal to hexadecimal conversion logic
    public static String decimalToHex(int decimal) {
        String hex = "";
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            hex = hexChars[decimal % 16] + hex;
            decimal /= 16;
        }
        return hex.length() == 0 ? "0" : hex;
    }

    // Handle basic arithmetic operations
    public static void handleOperations(Scanner input) {
        System.out.println("Enter the first number:");
        String num1 = input.next();

        System.out.println("Enter the number system of the first number (binary, octal, decimal, hexadecimal):");
        String fromSystemStr1 = input.next().toLowerCase();
        NumberSystem fromSystem1 = NumberSystem.fromString(fromSystemStr1);

        if (fromSystem1 == null || !fromSystem1.isValidInput(num1)) {
            System.out.println("Invalid input for the specified number system!");
            return;
        }

        System.out.println("Enter the operator (+, -, *, /):");
        char operatorSymbol = input.next().charAt(0);

        Operator operator = Operator.getOperator(operatorSymbol);
        if (operator == null) {
            System.out.println("Invalid operator! Please enter one of (+, -, *, /).");
            return;
        }

        System.out.println("Enter the second number:");
        String num2 = input.next();

        System.out.println("Enter the number system of the second number (binary, octal, decimal, hexadecimal):");
        String fromSystemStr2 = input.next().toLowerCase();
        NumberSystem fromSystem2 = NumberSystem.fromString(fromSystemStr2);

        if (fromSystem2 == null || !fromSystem2.isValidInput(num2)) {
            System.out.println("Invalid input for the specified number system!");
            return;
        }

        // Convert both numbers to decimal
        int decimalNum1 = convertToDecimal(num1, fromSystem1);
        int decimalNum2 = convertToDecimal(num2, fromSystem2);

        // Perform the operation
        int result = 0;
        switch (operator) {
            case ADD:
                result = decimalNum1 + decimalNum2;
                break;
            case SUBTRACT:
                result = decimalNum1 - decimalNum2;
                break;
            case MULTIPLY:
                result = decimalNum1 * decimalNum2;
                break;
            case DIVIDE:
                if (decimalNum2 != 0) {
                    result = decimalNum1 / decimalNum2;
                } else {
                    System.out.println("Cannot divide by zero!");
                    return;
                }
                break;
        }

        // Show the result
        System.out.println("Result in decimal: " + result);

        // Convert result back to the desired number system (if needed)
        System.out.println("Convert result to which number system? (binary, octal, hexadecimal):");
        String toSystemStr = input.next().toLowerCase();
        NumberSystem toSystem = NumberSystem.fromString(toSystemStr);
        String convertedResult = convertFromDecimal(result, toSystem);
        System.out.println("Result in " + toSystemStr + ": " + convertedResult);
    }
    // Convert a number from various systems to decimal
    public static int convertToDecimal(String number, NumberSystem fromSystem) {
        int decimal = 0;

        switch (fromSystem) {
            case BINARY:
                for (int i = 0; i < number.length(); i++) {
                    decimal = decimal * 2 + (number.charAt(i) - '0');
                }
                break;
            case OCTAL:
                for (int i = 0; i < number.length(); i++) {
                    decimal = decimal * 8 + (number.charAt(i) - '0');
                }
                break;
            case DECIMAL:
                decimal = Integer.parseInt(number);
                break;
            case HEXADECIMAL:
                for (int i = 0; i < number.length(); i++) {
                    char hexChar = number.charAt(i);
                    if (hexChar >= '0' && hexChar <= '9') {
                        decimal = decimal * 16 + (hexChar - '0');
                    } else if (hexChar >= 'A' && hexChar <= 'F') {
                        decimal = decimal * 16 + (hexChar - 'A' + 10);
                    } else if (hexChar >= 'a' && hexChar <= 'f') {
                        decimal = decimal * 16 + (hexChar - 'a' + 10);
                    }
                }
                break;
            default:
                System.out.println("Invalid number system!");
                break;
        }

        return decimal;
    }

    // Convert a decimal number to various systems
    public static String convertFromDecimal(int decimal, NumberSystem toSystem) {
        String result = ""; // Initialize an empty string to build the result

        switch (toSystem) {
            case BINARY:
                if (decimal == 0) {
                    return "0"; // Handle the case where the input is 0
                }
                while (decimal > 0) {
                    result = (decimal % 2) + result; // Prepend the new digit
                    decimal /= 2;
                }
                break;
            case OCTAL:
                if (decimal == 0) {
                    return "0"; // Handle the case where the input is 0
                }
                while (decimal > 0) {
                    result = (decimal % 8) + result; // Prepend the new digit
                    decimal /= 8;
                }
                break;
            case HEXADECIMAL:
                if (decimal == 0) {
                    return "0"; // Handle the case where the input is 0
                }
                char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                while (decimal > 0) {
                    result = hexChars[decimal % 16] + result; // Prepend the new digit
                    decimal /= 16;
                }
                break;
            case DECIMAL:
                return Integer.toString(decimal); // You can keep this as is
            default:
                System.out.println("Invalid number system!");
                break;
        }

        if (result.length() == 0) {
            return "0"; // If the result is empty, return "0"
        }
        return result; // Return the constructed string
    }
}



