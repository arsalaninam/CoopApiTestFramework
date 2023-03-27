package org.coop.testcase.toiletSeatDownEndpoint;

import org.coop.businesslayer.errorResponse.ErrorResponseBusinessLogic;
import org.coop.businesslayer.successResponse.SuccessResponseBusinessLogic;
import org.coop.data.commonDataProvider.CommonDataProvider;
import org.coop.data.toiletSeatDownDataProvider.ToiletSeatDownDataProvider;
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

public class ToiletSeatDownTestcase extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(BarnUnlockTestcase.class);
    private final String clientId = prop.getProperty(CLIENT_ID);
    private final String clientSecret = prop.getProperty(CLIENT_SECRET);
    private final String toiletSeatDownEndpoint = prop.getProperty(TOILET_SEAT_DOWN_ENDPOINT);
    private final String validUserId = prop.getProperty(VALID_USER_ID);
    private final String invalidUserId = prop.getProperty(INVALID_USER_ID);
    private final String baseUrl = prop.getProperty(BASE_URL);
    private final String urlWithValidUserId = baseUrl + validUserId + toiletSeatDownEndpoint;
    private final String urlWithInvalidUserId = baseUrl + invalidUserId + toiletSeatDownEndpoint;


    private final String accessToken = getAccessToken(clientId, clientSecret);

    /*****************************************************************
     * Send a POST request to /toiletseat-down and validate response code
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
     * Send a POST request to /toiletseat-down and Validate that response
     * returns the expected action, success flag and message
     *****************************************************************************/

    @Test(dependsOnMethods = {"testResponseStatusCode200"},
            dataProvider = "toiletSeatDownSuccessResponse",
            dataProviderClass = ToiletSeatDownDataProvider.class)
    public void testSuccessResponseOfToiletSeatDown(String action, boolean successOrNot,
                                                    String message) {
        log.info(VALIDATE_SUCCESS_RESPONSE_BODY + urlWithValidUserId);
        SuccessResponse toiletSeatDownResponse = SuccessResponseBusinessLogic.
                performAction(accessToken, urlWithValidUserId);

        softAssert.assertEquals(toiletSeatDownResponse.getAction(), action);
        softAssert.assertEquals(toiletSeatDownResponse.isSuccess(), successOrNot);
        softAssert.assertTrue(message.contains(toiletSeatDownResponse.getMessage()));
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a Post request to /toiletseat-down with invalid usersId
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
     * Send a Post request to /toiletseat-down with invalid accessToken
     * & validUserId and Validate error response of invalid_token
     **************************************************************/

    @Test(dataProvider = "invalidAccessTokenErrorResponse",
            dataProviderClass = CommonDataProvider.class)
    public void testErrorResponseWithInvalidAccessToken(String error, String errorDescription,
                                                        String invalidAccessToken) {
        log.info(VALIDATE_ERROR_RESPONSE_BODY + urlWithValidUserId);
        InvalidTokenErrorResponse invalidTokenErrorResponse = ErrorResponseBusinessLogic
                .invalidTokenPerformAction(invalidAccessToken, urlWithValidUserId);

        softAssert.assertEquals(invalidTokenErrorResponse.getError(), error);
        softAssert.assertEquals(invalidTokenErrorResponse.getErrorDescription(), errorDescription);
        softAssert.assertAll();
    }
}
