package com.example.demo;

import com.example.consumingwebservice.wsdl.*;
import com.example.demo.exeptions.ErrorResponse;
import com.example.demo.exeptions.SoapClientException;
import com.example.demo.exeptions.ViolationOfVerificationResponse;
import com.example.demo.soapclient.CalculatorClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Tag(
        name="Calculator controller",
        description="REST адаптер к SOAP сервису http://www.dneonline.com/calculator.asmx."
)
@Validated
@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "500",content = @Content(schema = @Schema(
                implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400",content = @Content(schema = @Schema(
                implementation = ViolationOfVerificationResponse.class)))

})
public class CalculatorController {

    private final CalculatorClient soapClient;

    CalculatorController(CalculatorClient soapClient) {
        this.soapClient = soapClient;
    }

    @Operation(
            summary = "Сложение",
            description = "Складывает числа A и B"
    )
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(
                    implementation = AddResponse.class)))
    })
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

    @Operation(
            summary = "Деление",
            description = "Делит число A на число B"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(
                    implementation = DivideResponse.class)))
    })
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

    @Operation(
            summary = "Умножение",
            description = "Умножает числа A и B"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(
                    implementation = MultiplyResponse.class)))
    })
    @RequestMapping(path = "/multiply", method = RequestMethod.GET)
    public ResponseEntity<?> getMultiply(@RequestParam @NotNull int A, @RequestParam @NotNull int B)
            throws SoapClientException{
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

    @Operation(
            summary = "Вычитание",
            description = "Вычитает число B из числа A"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(
                    implementation = SubtractResponse.class)))
    })
    @RequestMapping(path = "/subtract", method = RequestMethod.GET)
    public ResponseEntity<?> getSubtract(@RequestParam @NotNull int A, @RequestParam @NotNull int B)
            throws SoapClientException{
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
