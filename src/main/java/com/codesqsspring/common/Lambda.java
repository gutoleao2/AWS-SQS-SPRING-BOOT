package com.codesqsspring.common;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

@Data
public class Lambda<P> {

    private String function;
    private P request;
    private TypeReference<?> typeReference;

    public Lambda(final String function, final P request, final TypeReference<?> typeReference) {

        this.function = function;
        this.request = request;
        this.typeReference = typeReference;
    }
}
