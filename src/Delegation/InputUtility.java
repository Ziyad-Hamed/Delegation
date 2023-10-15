package Delegation;

public class InputUtility {
	public static String cleanAndValidateInput(String input) {
	    // Check if the input is enclosed in double quotes or single quotes and remove them
	    if (input.matches("^(\"[^\"]+\"|'[^']+')$")) {
	        input = input.substring(1, input.length() - 1);
	    }

	    // Remove any leading/trailing whitespace before validation
	    return input.trim();
	}
}
