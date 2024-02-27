package com.jscarfsbg.customerexperimentservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteExperimentResponse {
    private boolean deleted;
    private long removedFrom;
    private String id;

    public DeleteExperimentResponse(boolean deleted, long removedFrom, String id) {
        this.deleted = deleted;
        this.removedFrom = removedFrom;
        this.id = id;
    }
}
