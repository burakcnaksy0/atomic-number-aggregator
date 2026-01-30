package com.burakcanaksoy.atomicnumberaggregator.service.impl;

import com.burakcanaksoy.atomicnumberaggregator.dto.NumberRequest;
import com.burakcanaksoy.atomicnumberaggregator.dto.NumberResponse;
import com.burakcanaksoy.atomicnumberaggregator.exception.SoapServiceException;
import com.burakcanaksoy.atomicnumberaggregator.model.Analysis;
import com.burakcanaksoy.atomicnumberaggregator.model.Identity;
import com.burakcanaksoy.atomicnumberaggregator.model.Statistics;
import com.burakcanaksoy.atomicnumberaggregator.service.NumberService;
import com.burakcanaksoy.numberconversion.wsdl.NumberConversionSoapType;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Service
public class NumberServiceImpl implements NumberService {

    private final NumberConversionSoapType soapClient;

    public NumberServiceImpl(NumberConversionSoapType soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    public NumberResponse getNumberDetails(NumberRequest request) {
        BigInteger number = request.getNumber();

        try {
            CompletableFuture<String> wordsFuture = CompletableFuture
                    .supplyAsync(() -> soapClient.numberToWords(number));

            CompletableFuture<String> dollarsFuture = CompletableFuture
                    .supplyAsync(() -> soapClient.numberToDollars(new BigDecimal(number)));

            CompletableFuture<Analysis> analysisFuture = CompletableFuture
                    .supplyAsync(() -> performLocalAnalysis(number));

            // Tüm future'ların bitmesini bekle
            CompletableFuture.allOf(wordsFuture, dollarsFuture, analysisFuture).join();

            String numberAsText = wordsFuture.get();
            String dollarFormat = dollarsFuture.get();
            Analysis analysis = analysisFuture.get();

            // İstatistik hesaplama (Text geldikten sonra yapılır)
            Statistics statistics = calculateStatistics(numberAsText);

            // Response oluşturma
            Identity identity = new Identity(number, numberAsText, dollarFormat);

            NumberResponse response = new NumberResponse();
            response.setIdentity(identity);
            response.setAnalysis(analysis);
            response.setStatistics(statistics);

            return response;

        } catch (Exception e) {
            throw new SoapServiceException("Error occurred while aggregating number details", e);
        }
    }

    private Analysis performLocalAnalysis(BigInteger number) {
        int digitCount = number.abs().toString().length();
        boolean isEven = number.mod(BigInteger.TWO).equals(BigInteger.ZERO);
        boolean isPositive = number.compareTo(BigInteger.ZERO) > 0;
        return new Analysis(digitCount, isEven, isPositive);
    }

    private Statistics calculateStatistics(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new Statistics(0, 0);
        }
        String cleanedText = text.trim();
        int charLength = cleanedText.length();
        int wordCount = cleanedText.split("\\s+").length;
        return new Statistics(charLength, wordCount);
    }
}
/*
            CompletableFuture<String> wordsFuture = CompletableFuture
                    .supplyAsync(() -> soapClient.numberToWords(number));

            CompletableFuture<String> dollarsFuture = CompletableFuture
                    .supplyAsync(() -> soapClient.numberToDollars(new BigDecimal(number)));

            CompletableFuture<Analysis> analysisFuture = CompletableFuture
                    .supplyAsync(() -> performLocalAnalysis(number));

            // Tüm future'ların bitmesini bekle
            CompletableFuture.allOf(wordsFuture, dollarsFuture, analysisFuture).join();

            String numberAsText = wordsFuture.get();
            String dollarFormat = dollarsFuture.get();
            Analysis analysis = analysisFuture.get();

            // İstatistik hesaplama (Text geldikten sonra yapılır)
            Statistics statistics = calculateStatistics(numberAsText);

            // Response oluşturma
            Identity identity = new Identity(number, numberAsText, dollarFormat);

            NumberResponse response = new NumberResponse();
            response.setIdentity(identity);
            response.setAnalysis(analysis);
            response.setStatistics(statistics);

            return response;
 */
/*
String numberAsText = soapClient.numberToWords(number);
            String dollarFormat = soapClient.numberToDollars(new BigDecimal(number));
            Analysis analysis = performLocalAnalysis(number);

            Statistics statistics = calculateStatistics(numberAsText);

            Identity identity = new Identity(number, numberAsText, dollarFormat);

            NumberResponse response = new NumberResponse();
            response.setIdentity(identity);
            response.setAnalysis(analysis);
            response.setStatistics(statistics);

            return response;
 */