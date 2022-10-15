package com.mheducation.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class ApplicatationResourceAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ApiError handleBusinessException(BusinessException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleFeignException(FeignException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleConnectException(ConnectException ex) {
        return new ApiError("Communication failure with external service:"+ex.getMessage());
    }

}
