package com.codesqsspring.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericLambdaInvoke {

    private final ObjectMapper objectMapper;
    private final JsonLambdaClientWrapper jsonLambdaClientWrapper;

    public <T> T invokeFunction(final Lambda<?> lambdaRequest) throws Exception {

        try {
            final String responseLambda = InvokeFunction(lambdaRequest.getFunction(), lambdaRequest.getRequest());

            return (T) objectMapper.readValue(responseLambda, lambdaRequest.getTypeReference());

        } catch (JsonProcessingException e) {
            throw new Exception(lambdaRequest.getFunction());
        }
    }

    private String InvokeFunction(final String functionName, final Object request) throws Exception {

        final LambdaRequest lambdaRequest = LambdaRequest.builder().body(request).build();

        try {

            final String requestAsJson = objectMapper.writeValueAsString(lambdaRequest);

            return jsonLambdaClientWrapper.callLambda(functionName, requestAsJson);

        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }
    }
}
