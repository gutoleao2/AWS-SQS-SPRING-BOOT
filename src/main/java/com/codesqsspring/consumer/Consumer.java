package com.codesqsspring.consumer;

import com.codesqsspring.common.GenericLambdaInvoke;
import com.codesqsspring.common.Lambda;
import com.codesqsspring.common.LambdaBody;
import com.codesqsspring.common.LambdaTypeReferenceFactory;
import com.codesqsspring.model.ClientRequest;
import com.codesqsspring.model.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {


    private final GenericLambdaInvoke genericLambdaInvoke;
    private final String lambdaFuncktion;

    public Consumer(final GenericLambdaInvoke genericLambdaInvoke, @Value("${app.lambda.client.register}") final String lambdaFuncktion) {

        this.genericLambdaInvoke = genericLambdaInvoke;
        this.lambdaFuncktion = lambdaFuncktion;
    }


    @SqsListener("myQueueTest")
    public void loadMessagesFromQueue(final ClientRequest message) throws Exception {
        invokeLambda(message);
    }

    private ClientResponse invokeLambda(final ClientRequest clientRequest) throws Exception {

        final Lambda<ClientRequest> lambdaRequest = new Lambda<>(lambdaFuncktion, clientRequest, LambdaTypeReferenceFactory.getClientLambdaResponse());

        final LambdaBody<ClientResponse> response = genericLambdaInvoke.invokeFunction(lambdaRequest);

        return response.getData();

    }


}
