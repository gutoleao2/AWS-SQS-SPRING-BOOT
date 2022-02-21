package com.codesqsspring.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ClientResponse {

    private Integer id;
    private String name;
    private String empresa;
}
