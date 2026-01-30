package com.burakcanaksoy.atomicnumberaggregator.config;

import com.burakcanaksoy.numberconversion.wsdl.NumberConversion;
import com.burakcanaksoy.numberconversion.wsdl.NumberConversionSoapType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfig {
    @Bean
    public NumberConversionSoapType numberConversionSoapType(){
        NumberConversion service = new NumberConversion();
        return service.getNumberConversionSoap();
    }
}
