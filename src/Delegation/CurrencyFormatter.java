package Delegation;

import java.math.BigInteger;

class CurrencyFormatter {
//    private NumberConvertable numberConverter;
//
//    public CurrencyFormatter(NumberConvertable numberConverter) {
//        this.numberConverter = numberConverter;
//    }

    
    private NumberConvertable numberConverter=   new BigIntegerConverter();
    
    public String formatCurrency(CurrencyValue currencyValue) {
        BigInteger pounds = currencyValue.getPounds();
        BigInteger piasters = currencyValue.getPiasters();

        String poundsWords = "";
        String piastersWords = "";

        if (pounds.compareTo(BigInteger.ZERO) > 0) {
            poundsWords = numberConverter.convertToArabicWords(pounds) + " جنيهاً  و ";
        }

        if (piasters.compareTo(BigInteger.ZERO) > 0) {
            piastersWords = numberConverter.convertToArabicWords(piasters) + " قرشاً  مصرياً ";
        } else {
            piastersWords = " صفر  قرشاً  مصرياً ";
        }

        return poundsWords + (pounds.compareTo(BigInteger.ZERO) > 0 && piasters.compareTo(BigInteger.ZERO) > 0 ? " و " : "") + piastersWords + "فقط لا غير ";
    }
}