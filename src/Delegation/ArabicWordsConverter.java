package Delegation;

class ArabicWordsConverter {
    private NumberConvertable numberConverter;

    public ArabicWordsConverter(NumberConvertable numberConverter) {
        this.numberConverter = numberConverter;
    }

    public String convertToArabicWords(int number) {
        return numberConverter.convertToArabicWords(number);
    }
}
