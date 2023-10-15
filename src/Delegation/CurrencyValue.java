package Delegation;

import java.math.BigInteger;

public class CurrencyValue {
    private BigInteger pounds;
    private BigInteger piasters;

    public CurrencyValue(BigInteger pounds, BigInteger piasters) {
        this.pounds = pounds;
        this.piasters = piasters;
    }

    public BigInteger getPounds() {
        return pounds;
    }

    public BigInteger getPiasters() {
        return piasters;
    }
}
