package org.coop.pogo.success;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {

    private String action;
    private boolean success;
    private String message;
    private String data;

    @Override
    public String toString() {
        return "ClassPojo [action = " + action + ", success = " + success + ", message = " + message + ", data = " + data + "]";
    }
}
