package com.burakcanaksoy.atomicnumberaggregator.model;

import lombok.Data;

@Data
public class Analysis {
    private int digitCount;
    private boolean isEven;
    private boolean isPositive;

    public Analysis(int digitCount,boolean isEven,boolean isPositive){
        this.digitCount = digitCount;
        this.isEven = isEven;
        this.isPositive = isPositive;
    }
}
