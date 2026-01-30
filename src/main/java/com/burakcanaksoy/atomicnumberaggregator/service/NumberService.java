package com.burakcanaksoy.atomicnumberaggregator.service;

import com.burakcanaksoy.atomicnumberaggregator.dto.NumberRequest;
import com.burakcanaksoy.atomicnumberaggregator.dto.NumberResponse;

public interface NumberService {
    NumberResponse getNumberDetails(NumberRequest request);
}
