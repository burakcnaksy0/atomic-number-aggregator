package com.burakcanaksoy.atomicnumberaggregator.dto;

import com.burakcanaksoy.atomicnumberaggregator.model.Analysis;
import com.burakcanaksoy.atomicnumberaggregator.model.Identity;
import com.burakcanaksoy.atomicnumberaggregator.model.Statistics;
import lombok.Data;

@Data
public class NumberResponse {
    private Identity identity;
    private Analysis analysis;
    private Statistics statistics;
}