package com.mheducation.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class RetreiveMessageErrorDecoder  implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        String responseBody = "not found";
        if (404 == response.status()) {
            return new IllegalArgumentException(responseBody);
        }
        return defaultErrorDecoder.decode(s, response);
    }
}
