package org.coop.testcase.countEggsEndpoint;

import org.coop.businesslayer.errorResponse.ErrorResponseBusinessLogic;
import org.coop.data.commonDataProvider.CommonDataProvider;
import org.coop.pogo.error.InsufficientScopeErrorResponse;
import org.coop.testcase.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.coop.constant.ScenarioNameConstant.VALIDATE_ERROR_RESPONSE_BODY;
import static org.coop.constant.ServiceConstant.*;

public class CountEggsTestcase extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(CountEggsTestcase.class);
    private final String clientId = prop.getProperty(CLIENT_ID);
    private final String clientSecret = prop.getProperty(CLIENT_SECRET);
    private final String countEggsEndpoint = prop.getProperty(COUNT_EGGS_ENDPOINT);
    private final String validUserId = prop.getProperty(VALID_USER_ID);
    private final String baseUrl = prop.getProperty(BASE_URL);
    private final String urlWithValidUserId = baseUrl + validUserId + countEggsEndpoint;


    private final String accessToken = getAccessToken(clientId, clientSecret);


    /***************************************************************
     * Send a Post request to /eggs-count with valid accessToken
     * & validUserId that does not have scope of eggs count
     * and Validate error response of insufficient_scope
     **************************************************************/

    @Test(dataProvider = "insufficientScopeErrorResponse",
            dataProviderClass = CommonDataProvider.class)
    public void testErrorResponseWithInsufficientScopeForUser(String error, String errorDescription) {
        log.info(VALIDATE_ERROR_RESPONSE_BODY + urlWithValidUserId);
        InsufficientScopeErrorResponse insufficientScopeErrorResponse = ErrorResponseBusinessLogic
                .insufficientScopePerformAction(accessToken, urlWithValidUserId);

        softAssert.assertEquals(insufficientScopeErrorResponse.getError(), error);
        softAssert.assertEquals(insufficientScopeErrorResponse.getErrorDescription(), errorDescription);
        softAssert.assertAll();
    }
}
