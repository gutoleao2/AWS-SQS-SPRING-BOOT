package com.codesqsspring.controller;

import com.codesqsspring.model.ClientRequest;
import com.codesqsspring.model.ClientResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/save")
public class SQSController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @PostMapping
    public ResponseEntity<Void> putMessagedToQueue(@RequestBody ClientRequest message) {

        Gson gson = new Gson();
        String json = gson.toJson(message);

        Message<String> msg = MessageBuilder.withPayload(json)
                .build();

        queueMessagingTemplate.send(endPoint, msg);

        return ResponseEntity.noContent().build();
    }

}
