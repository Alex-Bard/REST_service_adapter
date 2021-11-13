package com.example.demo.soapclient;


import com.example.demo.soapclient.CalculatorClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CalkulatorConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public CalculatorClient сalculatorClient(Jaxb2Marshaller jaxb2Marshaller) {
        CalculatorClient сalculatorClient = new CalculatorClient();

        сalculatorClient.setMarshaller(jaxb2Marshaller);
        сalculatorClient.setUnmarshaller(jaxb2Marshaller);
        return сalculatorClient;
    }
}