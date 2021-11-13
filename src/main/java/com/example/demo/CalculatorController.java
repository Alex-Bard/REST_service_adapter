package com.example.demo;

import com.example.consumingwebservice.wsdl.*;
import com.example.demo.exeptions.SoapClientException;
import com.example.demo.soapclient.CalculatorClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Validated
@RestController
public class CalculatorController {

    private final CalculatorClient soapClient;

    CalculatorController(CalculatorClient soapClient) {
        this.soapClient = soapClient;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public ResponseEntity<?> getAdd(@RequestParam @NotNull int A, @RequestParam @NotNull int B)
            throws SoapClientException{
        Add request = new Add();
        AddResponse response;
        request.setIntA(A);
        request.setIntB(B);
        try {
            response = soapClient.getResponse(request,'+');
        }
        catch (Exception e){
            throw new SoapClientException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),
                    "soap server error",e.getLocalizedMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @RequestMapping(path = "/divide", method = RequestMethod.GET)
    public ResponseEntity<?> getDivide(@RequestParam @NotNull int A, @RequestParam @NotNull int B)
            throws SoapClientException,ArithmeticException {

        if (B == 0)
            throw new ArithmeticException("divisor is zero");

        Divide request = new Divide();
        DivideResponse response;
        request.setIntA(A);
        request.setIntB(B);

        try {
            response = soapClient.getResponse(request,'/');
        }
        catch (Exception e){
            throw new SoapClientException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),
                    "soap server error",e.getLocalizedMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @RequestMapping(path = "/multiply", method = RequestMethod.GET)
    public ResponseEntity<?> getMultiply(@RequestParam @NotNull int A, @RequestParam @NotNull int B)throws SoapClientException{
        Multiply request = new Multiply();
        MultiplyResponse response;
        request.setIntA(A);
        request.setIntB(B);
        try {
            response = soapClient.getResponse(request,'*');
        }
        catch (Exception e){
            throw new SoapClientException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),
                    "soap server error",e.getLocalizedMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @RequestMapping(path = "/subtract", method = RequestMethod.GET)
    public ResponseEntity<?> getSubtract(@RequestParam @NotNull int A, @RequestParam @NotNull int B)throws SoapClientException{
        Subtract request = new Subtract();
        SubtractResponse response;
        request.setIntA(A);
        request.setIntB(B);
        try {
            response = soapClient.getResponse(request,'-');
        }
        catch (Exception e){
            throw new SoapClientException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),
                    "soap server error",e.getLocalizedMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
