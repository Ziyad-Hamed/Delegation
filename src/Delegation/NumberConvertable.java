package Delegation;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface NumberConvertable {
    String convertToArabicWords(int number);

    String convertToArabicWords(BigDecimal number);

    String convertToArabicWords(BigInteger number);
}
