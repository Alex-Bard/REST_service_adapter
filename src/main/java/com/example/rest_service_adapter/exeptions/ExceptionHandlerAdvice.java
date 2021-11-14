package com.example.rest_service_adapter.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SoapClientException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> onSoapClientException(SoapClientException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getTitle(), e.getDetail()), e.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ViolationOfVerificationResponse onMethodArgumentNotValidException(
            MethodArgumentTypeMismatchException e) {

        return new ViolationOfVerificationResponse(
                e.getParameter().getParameterName()
                , e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ViolationOfVerificationResponse onMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {

        return new ViolationOfVerificationResponse(
                e.getParameterName(), e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse onArithmeticException(
            ArithmeticException e) {

        return new ErrorResponse(
                "attempt to divide by zero", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ViolationOfVerificationResponse onException(
            Exception e) {

        return new ViolationOfVerificationResponse(
                "B", e.getMessage());
    }
}