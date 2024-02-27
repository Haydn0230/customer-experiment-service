package com.jscarfsbg.customerexperimentservice.dto;

import com.jscarfsbg.customerexperimentservice.exception.InputFailedValidation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SetExperimentRequest {
    private String name;
    private String type;

    public InputFailedValidation validate() {
        if (this.name == null)
            return new InputFailedValidation("name needed", 400);
        else if (this.type == null)
            return new InputFailedValidation("type needed", 400);
        else
            return null;
    }
}
