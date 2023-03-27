package org.coop.businesslayer.successResponse;

import org.coop.pogo.success.SuccessResponse;
import org.coop.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class SuccessResponseBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(SuccessResponseBusinessLogic.class);

    /**
     * Extract data as Success Response object from the POJO
     * <p>
     * e.g. https://coop.apps.symfonycasts.com/api/{userId}/{actionEndpoint}
     *
     * @param accessToken - access token for the particular user
     * @param url         - complete endpoint of action
     */
    public static SuccessResponse performAction(String accessToken, String url) {
        SuccessResponse successResponse = given().auth().oauth2(accessToken)
                .post(url).as(SuccessResponse.class);
        log.info("Message : " + successResponse.getMessage());
        return successResponse;
    }
}
