package org.coop.pogo.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("token_type")
    private String tokenType;
    private String scope;

    @Override
    public String toString() {
        return "ClassPojo [access_token = " + accessToken + ", expires_in = " + expiresIn + ", token_type = " + tokenType + ", scope = " + scope + "]";
    }
}
