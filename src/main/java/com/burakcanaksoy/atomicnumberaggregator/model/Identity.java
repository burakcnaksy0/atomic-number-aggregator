package com.burakcanaksoy.atomicnumberaggregator.model;

import java.math.BigInteger;
import lombok.Data;

@Data
public class Identity {
    private BigInteger originalNumber;
    private String numberAsText;
    private String dollarFormat;

    public Identity(BigInteger originalNumber, String numberAsText, String dollarFormat) {
        this.originalNumber = originalNumber;
        this.numberAsText = numberAsText;
        this.dollarFormat = dollarFormat;
    }
}
