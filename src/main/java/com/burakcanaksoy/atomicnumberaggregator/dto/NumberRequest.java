package com.burakcanaksoy.atomicnumberaggregator.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class NumberRequest {
    private BigInteger number;
}
