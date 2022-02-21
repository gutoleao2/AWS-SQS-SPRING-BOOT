package com.codesqsspring.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@Component
@RequiredArgsConstructor
public class JsonLambdaClientWrapper {

    public String callLambda(final String functionName, final String jsonRequest) {

        long startTime = System.nanoTime();

        try(var awsLambda = LambdaClient.builder()
                .region(Region.SA_EAST_1)
                .build()) {

            final SdkBytes payload = SdkBytes.fromUtf8String(jsonRequest);

            final InvokeRequest requestlambda = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(payload).build();

            final InvokeResponse res = awsLambda.invoke(requestlambda);


            return res.payload().asUtf8String();
        } catch (final Exception e) {
            throw e;
        }

    }
}
