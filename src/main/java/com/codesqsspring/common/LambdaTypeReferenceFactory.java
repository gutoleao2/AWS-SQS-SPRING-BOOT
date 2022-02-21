package com.codesqsspring.common;

import com.codesqsspring.model.ClientResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LambdaTypeReferenceFactory {

    public static TypeReference<LambdaBody<ClientResponse>> getClientLambdaResponse() {
        return new TypeReference<>() {};
    }
}
