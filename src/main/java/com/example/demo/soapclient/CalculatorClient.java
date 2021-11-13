package com.example.demo.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CalculatorClient extends WebServiceGatewaySupport {

    public <retT,recT> retT getResponse(recT request, char op)  {
        String action;
        switch (op){
            case '+':
                    action = "Add";
                    break;
            case '/' :
                    action = "Divide";
                    break;
            case '*':
                    action = "Multiply";
                    break;
            default :
                    action = "Subtract";
                    break;
        }

        return (retT) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request,
                        new SoapActionCallback(
                                "http://tempuri.org/"+ action));
    }

}
