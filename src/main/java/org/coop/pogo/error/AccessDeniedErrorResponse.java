package org.coop.pogo.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDeniedErrorResponse {
    private String error;
    @JsonProperty("error_message")
    private String errorMessage;

    @Override
    public String toString() {
        return "ClassPojo [error = " + error + ", errorMessage = " + errorMessage + "]";
    }
}
