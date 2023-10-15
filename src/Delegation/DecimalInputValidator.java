package Delegation;

public class DecimalInputValidator implements InputValidator {
    @Override
    public boolean isValid(String input) {
        // Remove double or single quotes before validation
        input = input.replaceAll("[\"']+", "");

        try {
            // Attempt to parse the input as a BigDecimal
            java.math.BigDecimal bigDecimal = new java.math.BigDecimal(input);

            // Check if the input has 2 or fewer decimal places
            java.math.BigDecimal remainder = bigDecimal.remainder(java.math.BigDecimal.ONE);
            int decimalPlaces = remainder.scale();
            return decimalPlaces <= 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
