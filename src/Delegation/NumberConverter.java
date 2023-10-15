package Delegation;

import java.math.BigDecimal;
import java.math.BigInteger;

class BigIntegerConverter implements NumberConvertable {
    private static final String[] units = {"", " ألف ", " مليون ", "مليار ", " تريليون ", " كوادريليون ", " كوينتليون "};
    private static final String[] hundreds = {"", "مائة", "مئتان", "ثلاثمائة", "أربعمائة", "خمسمائة", "ستمائة", "سبعمائة", "ثمانمائة", "تسعمائة"};
	private static final String[] tens = {"", " عشرة ", " عشرون ", " ثلاثون ", " أربعون ", " خمسون ", " ستون ", " سبعون ", " ثمانون ", " تسعون "};
    private static final String[] ones = {"", " واحد ", " اثنان ", " ثلاثة ", " أربعة ", " خمسة ", " ستة ", " سبعة ", " ثمانية ", " تسعة "};

    @Override
    public String convertToArabicWords(BigDecimal number) {
        return convertToArabicWords(number.toBigInteger());
    }

    @Override
    public String convertToArabicWords(int number) {
        return convertToArabicWords(BigInteger.valueOf(number));
    }

    @Override
    public String convertToArabicWords(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) == 0) {
            return "صفر";
        }

        StringBuilder result = new StringBuilder();
        int groupCount = 0;

        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] quotientAndRemainder = number.divideAndRemainder(BigInteger.valueOf(1000));
            BigInteger threeDigits = quotientAndRemainder[1];

            if (threeDigits.compareTo(BigInteger.ZERO) > 0) {
                if (groupCount > 0) {
                    result.insert(0, " و");
                }

                if (groupCount == 1 && threeDigits.compareTo(BigInteger.valueOf(2)) == 0) {
                    result.insert(0, " ألفان ");
                } else {
                    result.insert(0, convertThreeDigitsToWords(threeDigits) + " " + units[groupCount]);
                }
            }

            number = quotientAndRemainder[0];
            groupCount++;
        }

        return result.toString().trim();
    }

    private String convertThreeDigitsToWords(BigInteger number) {
        StringBuilder result = new StringBuilder();
        int intValue = number.intValue();
        int hundredss = intValue / 100;
        int remainingDigits = intValue % 100;

        if (hundredss > 0) {
        	result.append(hundreds[hundredss]);
            if (remainingDigits > 0) {
                result.append(" و ");
            }
        }

        if (remainingDigits >= 10 && remainingDigits <= 19) {
            if (remainingDigits == 10) {
                result.append(" عشرة ");
            } else if (remainingDigits == 11) {
                result.append(" أحد عشر ");
            } else if (remainingDigits == 12) {
                result.append(" اثنا عشر ");
            } else {
                result.append(ones[remainingDigits - 10]).append(" عشر ");
            }
        } else {
            int tensDigit = remainingDigits / 10;
            int onesDigit = remainingDigits % 10;
            if (onesDigit > 0) {
                result.append(ones[onesDigit]);
                if (tensDigit > 0) {
                    result.append(" و");
                }
            }
            if (tensDigit > 0) {
                result.append(tens[tensDigit]);
            }
        }

        return result.toString();
    }
}
