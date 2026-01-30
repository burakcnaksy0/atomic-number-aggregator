package com.burakcanaksoy.atomicnumberaggregator.model;

import lombok.Data;

@Data
public class Statistics {
    private int characterLength;
    private int wordCount;

    public Statistics(int characterLength,int wordCount){
        this.characterLength = characterLength;
        this.wordCount = wordCount;
    }
}