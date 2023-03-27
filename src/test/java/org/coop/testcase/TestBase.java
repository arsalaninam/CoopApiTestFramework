package org.coop.testcase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.coop.pogo.token.TokenResponse;
import org.coop.util.ObjectFactory;
import org.coop.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.coop.constant.ServiceConstant.GRANT_TYPE;
import static org.coop.constant.ServiceConstant.TOKEN_ENDPOINT;

public class TestBase extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    private final String grantType = prop.getProperty(GRANT_TYPE);

    // Singleton instance of softAssert
    protected SoftAssert softAssert = ObjectFactory.getSoftAssert();

    @BeforeClass
    public static void setUp() {
        requestSpecification = new RequestSpecBuilder().
                build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                build();
    }

    protected String getAccessToken(String clientId, String clientSecret) {
        String tokenEndpoint = prop.getProperty(TOKEN_ENDPOINT);
        log.info("URL to be hit : " + tokenEndpoint);

        TokenResponse tokenResponse = given().contentType(ContentType.URLENC)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret).
                post(tokenEndpoint).then().extract().
                response().getBody().as(TokenResponse.class);
        log.info("Info : " + tokenResponse);

        return tokenResponse.getAccessToken();
    }
}
