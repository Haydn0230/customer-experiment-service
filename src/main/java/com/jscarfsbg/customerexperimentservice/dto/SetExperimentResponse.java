package com.jscarfsbg.customerexperimentservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SetExperimentResponse {
    private String experimentId;

    public SetExperimentResponse(String experimentId) {
        this.experimentId = experimentId;
    }
}
