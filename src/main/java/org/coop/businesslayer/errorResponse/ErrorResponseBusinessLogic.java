package org.coop.businesslayer.errorResponse;

import org.coop.pogo.error.AccessDeniedErrorResponse;
import org.coop.pogo.error.InsufficientScopeErrorResponse;
import org.coop.pogo.error.InvalidTokenErrorResponse;
import org.coop.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class ErrorResponseBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(ErrorResponseBusinessLogic.class);

    /**
     * Extract data as Error Response object from the POJO
     * e.g. https://coop.apps.symfonycasts.com/api/{userId}/{actionEndpoint}
     *
     * @param accessToken - access token for the particular user
     * @param url         - complete endpoint of action
     */
    public static AccessDeniedErrorResponse accessDeniedPerformAction(String accessToken, String url) {
        AccessDeniedErrorResponse accessDeniedErrorResponse = given().auth().oauth2(accessToken)
                .post(url).as(AccessDeniedErrorResponse.class);
        log.info("Error Message : " + accessDeniedErrorResponse.getError());
        return accessDeniedErrorResponse;
    }

    public static InvalidTokenErrorResponse invalidTokenPerformAction(String accessToken, String url) {
        InvalidTokenErrorResponse invalidTokenErrorResponse = given().auth().oauth2(accessToken)
                .post(url).as(InvalidTokenErrorResponse.class);
        log.info("Error Description : " + invalidTokenErrorResponse.getError());
        return invalidTokenErrorResponse;
    }

    public static InsufficientScopeErrorResponse insufficientScopePerformAction(String accessToken, String url) {
        InsufficientScopeErrorResponse invalidTokenErrorResponse = given().auth().oauth2(accessToken)
                .post(url).as(InsufficientScopeErrorResponse.class);
        log.info("Error Description : " + invalidTokenErrorResponse.getError());
        return invalidTokenErrorResponse;
    }

}
