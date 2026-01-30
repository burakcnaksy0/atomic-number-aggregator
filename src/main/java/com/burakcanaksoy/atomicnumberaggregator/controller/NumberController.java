package com.burakcanaksoy.atomicnumberaggregator.controller;

import com.burakcanaksoy.atomicnumberaggregator.dto.ApiResponse;
import com.burakcanaksoy.atomicnumberaggregator.dto.NumberRequest;
import com.burakcanaksoy.atomicnumberaggregator.dto.NumberResponse;
import com.burakcanaksoy.atomicnumberaggregator.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/numbers")
public class NumberController {

    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/details")
    public ResponseEntity<ApiResponse<NumberResponse>> getNumberDetails(@RequestBody NumberRequest request) {
        NumberResponse responseData = numberService.getNumberDetails(request);
        return ResponseEntity.ok(ApiResponse.success("Sayı bilgileri başarıyla getirildi.", responseData));
    }

    @PostMapping("/info")
    public ResponseEntity<ApiResponse<NumberResponse>> getNumberInfo(@RequestBody NumberRequest request) {
        NumberResponse responseData = numberService.getNumberDetails(request);
        return ResponseEntity.ok(ApiResponse.success("Sayı bilgileri başarıyla getirildi.", responseData));
    }

    @PostMapping("/position")
    public ResponseEntity<ApiResponse<NumberResponse>> getNumberPosition(@RequestBody NumberRequest request) {
        NumberResponse responseData = numberService.getNumberDetails(request);
        return ResponseEntity.ok(ApiResponse.success("Sayı konumu başarıyla getirildi.", responseData));
    }
}
     