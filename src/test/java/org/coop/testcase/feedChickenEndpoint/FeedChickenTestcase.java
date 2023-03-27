package org.coop.testcase.feedChickenEndpoint;

import org.coop.businesslayer.errorResponse.ErrorResponseBusinessLogic;
import org.coop.businesslayer.successResponse.SuccessResponseBusinessLogic;
import org.coop.data.commonDataProvider.CommonDataProvider;
import org.coop.data.feedChickenDataProvider.FeedChickenDataProvider;
import org.coop.pogo.error.AccessDeniedErrorResponse;
import org.coop.pogo.error.InvalidTokenErrorResponse;
import org.coop.pogo.success.SuccessResponse;
import org.coop.testcase.TestBase;
import org.coop.testcase.barnEndpoint.BarnUnlockTestcase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.coop.constant.ScenarioNameConstant.*;
import static org.coop.constant.ServiceConstant.*;

public class FeedChickenTestcase extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(BarnUnlockTestcase.class);
    private final String clientId = prop.getProperty(CLIENT_ID);
    private final String clientSecret = prop.getProperty(CLIENT_SECRET);
    private final String feedChickenEndpoint = prop.getProperty(FEED_CHICKEN_ENDPOINT);
    private final String validUserId = prop.getProperty(VALID_USER_ID);
    private final String invalidUserId = prop.getProperty(INVALID_USER_ID);
    private final String baseUrl = prop.getProperty(BASE_URL);
    private final String urlWithValidUserId = baseUrl + validUserId + feedChickenEndpoint;
    private final String urlWithInvalidUserId = baseUrl + invalidUserId + feedChickenEndpoint;


    private final String accessToken = getAccessToken(clientId, clientSecret);

    /*****************************************************************
     * Send a POST request to /chickens-feed and validate response code
     ****************************************************************/
    @Test
    public void testResponseStatusCode200() {
        log.info(VALIDATE_STATUS_CODE_200 + urlWithValidUserId);

        System.out.println(urlWithValidUserId);

        given().auth().oauth2(accessToken).
                spec(requestSpecification).
                when().
                post(urlWithValidUserId).
                then().
                spec(responseSpecification);
    }

    /*****************************************************************************
     * Send a POST request to /chickens-feed and Validate that response
     * returns the expected action, success flag and message
     *****************************************************************************/

    @Test(dependsOnMethods = {"testResponseStatusCode200"},
            dataProvider = "feedChickenSuccessResponse",
            dataProviderClass = FeedChickenDataProvider.class)
    public void testSuccessResponseOfFeedChicken(String action, boolean successOrNot) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + urlWithValidUserId);
        SuccessResponse feedChickenResponse = SuccessResponseBusinessLogic.performAction(accessToken, urlWithValidUserId);

        softAssert.assertEquals(feedChickenResponse.getAction(), action);
        softAssert.assertEquals(feedChickenResponse.isSuccess(), successOrNot);
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a Post request to /chickens-feed with invalid usersId
     * and Validate error response of access_denied
     **************************************************************/

    @Test(dataProvider = "invalidUserIdErrorResponse",
            dataProviderClass = CommonDataProvider.class)
    public void testErrorResponseWithInvalidUsersId(String error, String errorMessage) {
        log.info(VALIDATE_ERROR_RESPONSE_BODY + urlWithInvalidUserId);
        AccessDeniedErrorResponse accessDeniedErrorResponse = ErrorResponseBusinessLogic
                .accessDeniedPerformAction(accessToken, urlWithInvalidUserId);

        softAssert.assertEquals(accessDeniedErrorResponse.getError(), error);
        softAssert.assertEquals(accessDeniedErrorResponse.getErrorMessage(), errorMessage);
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a Post request to /chickens-feed with invalid accessToken
     * & invalidUserId and Validate error response of invalid_token
     **************************************************************/

    @Test(dataProvider = "invalidAccessTokenErrorResponse",
            dataProviderClass = CommonDataProvider.class)
    public void testErrorResponseWithInvalidAccessToken(String error, String errorDescription,
                                                        String invalidAccessToken) {
        log.info(VALIDATE_ERROR_RESPONSE_BODY + urlWithInvalidUserId);
        InvalidTokenErrorResponse invalidTokenErrorResponse = ErrorResponseBusinessLogic
                .invalidTokenPerformAction(invalidAccessToken, urlWithInvalidUserId);

        softAssert.assertEquals(invalidTokenErrorResponse.getError(), error);
        softAssert.assertEquals(invalidTokenErrorResponse.getErrorDescription(), errorDescription);
        softAssert.assertAll();
    }
}
