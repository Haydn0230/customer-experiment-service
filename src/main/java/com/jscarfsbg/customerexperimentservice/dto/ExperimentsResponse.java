package com.jscarfsbg.customerexperimentservice.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class ExperimentsResponse {
    private Date date = new Date();
    private List<Experiment> output;
    private String userId;

    public ExperimentsResponse(List<Experiment> output, String userId) {
        this.output = output;
        this.userId = userId;
    }
}
