package com.jscarfsbg.customerexperimentservice.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
public class Experiment {
    private String id = UUID.randomUUID().toString();
    private Date date = new Date();
    private String name;
    private String type;

    public Experiment(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
