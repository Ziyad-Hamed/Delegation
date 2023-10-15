package Delegation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new DecimalInputValidator();

        // Define the maximum allowed value as a BigInteger
        BigInteger maxAllowedValue = new BigInteger("999999999999999999999");

        while (true) {
            System.out.print("Enter a non-negative number (including decimals) or enter -1 to exit: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equals("-1")) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.print(" End ");
                break;
            }

            // Clean and validate the input
            String cleanedInput = InputUtility.cleanAndValidateInput(input);

            if (!inputValidator.isValid(cleanedInput)) {
                System.out.println("Invalid input. Please enter a valid non-negative decimal number.");
                continue;
            }

            BigDecimal inputNumber = null;

            try {
                // Check if input has quotes and remove them
                if (cleanedInput.startsWith("\"") && cleanedInput.endsWith("\"")) {
                    cleanedInput = cleanedInput.substring(1, cleanedInput.length() - 1);
                } else if (cleanedInput.startsWith("'") && cleanedInput.endsWith("'")) {
                    cleanedInput = cleanedInput.substring(1, cleanedInput.length() - 1);
                }

                // Try parsing as BigDecimal first
                inputNumber = new BigDecimal(cleanedInput);

                // Convert to BigInteger for comparison
                BigInteger inputAsBigInteger = inputNumber.toBigInteger();

                // Check if input exceeds the maximum allowed value
                if (inputAsBigInteger.compareTo(maxAllowedValue) > 0) {
                    System.out.println("You received the max number.");
                    continue;
                }

            } catch (NumberFormatException e) {
                // If parsing as BigDecimal or BigInteger fails, handle the error
                System.out.println("Invalid input. Please enter a valid non-negative number.");
                continue;
            }

            if (inputNumber.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Invalid input. Please enter a non-negative number.");
                continue;
            }

            CurrencyValue currencyValue = convertToCurrencyValue(inputNumber);
            CurrencyFormatter currencyFormatter = new CurrencyFormatter();
            String currencyWords = currencyFormatter.formatCurrency(currencyValue);

            System.out.println(currencyWords);
        }

        scanner.close();
    }

    private static CurrencyValue convertToCurrencyValue(BigDecimal inputNumber) {
        BigInteger totalValue = inputNumber.toBigInteger(); // Convert to BigInteger
        BigDecimal piastersDecimal = inputNumber.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        BigInteger piasters = piastersDecimal.toBigInteger(); // Convert to BigInteger

        return new CurrencyValue(totalValue, piasters);
    }

    // ... (Other methods within the Main class)
}
