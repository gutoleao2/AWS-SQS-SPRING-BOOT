package com.codesqsspring.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest implements Serializable {

    private String name;
    private String empresa;
}
