package org.coop.pogo.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientScopeErrorResponse {
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @Override
    public String toString() {
        return "ClassPojo [error = " + error + ", errorDescription = " + errorDescription + "]";
    }
}
