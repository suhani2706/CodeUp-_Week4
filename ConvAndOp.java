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
            return null;
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
            return null;
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
        System.out.println(Constants.CONVO_INPUT);
        String number = userInput.next();


        System.out.println(Constants.FROM_NUM_SYSTEM);

        String fromSystemStr1 = userInput.next().toLowerCase();
        NumberSystem fromSystem1 = NumberSystem.fromString(fromSystemStr1);

        if (fromSystem1 == null || !fromSystem1.isValidInput(number)) {
            System.out.println(Constants.INVALID_SYSTEM);
            return;
        }

        System.out.println(Constants.TO_NUM_SYSTEM);
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
                System.out.println(Constants.INVALID);
        }
    }


    // Convert input number to decimal based on its original system
    private static int convertToDecimal(String number, NumberSystem fromSystem, boolean showCalculations) {
        int decimalValue = -1;
        switch (fromSystem) {
            case BINARY:
                decimalValue = binaryToDecimal(number);
                if (showCalculations) {
                    System.out.println(Constants.BINARY_TO_DECIMAL + number + " = " + decimalValue);
                }
                break;
            case DECIMAL:
                decimalValue = Integer.parseInt(number);
                if (showCalculations) {
                    System.out.println(Constants.DECIMAL + decimalValue);
                }
                break;
            case OCTAL:
                decimalValue = octalToDecimal(number);
                if (showCalculations) {
                    System.out.println(Constants.OCTAL_TO_DECIMAL + number + " = " + decimalValue);
                }
                break;
            case HEXADECIMAL:
                decimalValue = hexToDecimal(number);
                if (showCalculations) {
                    System.out.println(Constants.HEX_T0_DECIMAL + number + " = " + decimalValue);
                }
                break;
            default:
                return -1;
        }
        return decimalValue;
    }

    // Convert decimal to the target system based on user input
    public static void convertToTargetSystem(int decimalValue, String toSystem) {
        switch (toSystem) {
            case "binary":
                String binaryResult = decimalToBinary(decimalValue);
                System.out.println(Constants.CONVERSION + binaryResult);

                break;
            case "octal":
                String octalResult = decimalToOctal(decimalValue);
                System.out.println(Constants.CONVERSION + octalResult);

                break;
            case "hexadecimal":
                String hexResult = decimalToHex(decimalValue);
                System.out.println(Constants.CONVERSION + hexResult);

                break;
            case "decimal":
                System.out.println(Constants.CONVERSION+ decimalValue);
                break;
            default:
                System.out.println(Constants.INVALID_TARGET);
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
        System.out.println(Constants.FIRST_INPUT_OPERATIONS);
        String num1 = input.next();

        System.out.println(Constants.OP_NUM_SYSTEM);
        String fromSystemStr1 = input.next().toLowerCase();
        NumberSystem fromSystem1 = NumberSystem.fromString(fromSystemStr1);

        if (fromSystem1 == null || !fromSystem1.isValidInput(num1)) {
            System.out.println(Constants.INVALID_SYSTEM);
            return;
        }

        System.out.println(Constants.OPERATOR);
        char operatorSymbol = input.next().charAt(0);

        Operator operator = Operator.getOperator(operatorSymbol);
        if (operator == null) {
            System.out.println(Constants.INVALID_OPERATOR);
            return;
        }

        System.out.println(Constants.SECOND_INPUT_OPERATIONS);
        String num2 = input.next();

        System.out.println(Constants.OP_NUM_SYSTEM);
        String fromSystemStr2 = input.next().toLowerCase();
        NumberSystem fromSystem2 = NumberSystem.fromString(fromSystemStr2);

        if (fromSystem2 == null || !fromSystem2.isValidInput(num2)) {
            System.out.println(Constants.INVALID_SYSTEM);
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
                    System.out.println(Constants.DIVIDE_BY_ZERO);
                    return;
                }
                break;
        }

        // Show the result
        System.out.println(Constants.RESULT_DECIMAL + result);

        // Convert result back to the desired number system (if needed)
        System.out.println(Constants.NUM_SYSTEM_RESULT);
        String toSystemStr = input.next().toLowerCase();
        NumberSystem toSystem = NumberSystem.fromString(toSystemStr);
        String convertedResult = convertFromDecimal(result, toSystem);
        System.out.println( Constants.RESULT+ toSystemStr + ": " + convertedResult);
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
                System.out.println(Constants.INVALID_NUM_SYSTEM);
                break;
        }

        return decimal;
    }

    // Convert a decimal number to various systems
    public static String convertFromDecimal(int decimal, NumberSystem toSystem) {
        String result = "";

        switch (toSystem) {
            case BINARY:
                if (decimal == 0) {
                    return "0";
                }
                while (decimal > 0) {
                    result = (decimal % 2) + result;
                    decimal /= 2;
                }
                break;
            case OCTAL:
                if (decimal == 0) {
                    return "0";
                }
                while (decimal > 0) {
                    result = (decimal % 8) + result;
                    decimal /= 8;
                }
                break;
            case HEXADECIMAL:
                if (decimal == 0) {
                    return "0";
                }
                char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                while (decimal > 0) {
                    result = hexChars[decimal % 16] + result;
                    decimal /= 16;
                }
                break;
            case DECIMAL:
                return Integer.toString(decimal);
            default:
                System.out.println(Constants.INVALID_NUM_SYSTEM);
                break;
        }

        if (result.length() == 0) {
            return "0";
        }
        return result;
    }
}



