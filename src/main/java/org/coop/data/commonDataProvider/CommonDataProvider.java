package org.coop.data.commonDataProvider;

import org.testng.annotations.DataProvider;

public class CommonDataProvider {
    @DataProvider
    public static Object[][] invalidUserIdErrorResponse() {
        return new Object[][]{
                {"access_denied",
                        "You do not have access to take this action on behalf of this user"}
        };
    }

    @DataProvider
    public static Object[][] invalidAccessTokenErrorResponse() {
        return new Object[][]{
                {"invalid_token",
                        "The access token provided is invalid", "invalidAccessToken"}
        };
    }

    @DataProvider
    public static Object[][] insufficientScopeErrorResponse() {
        return new Object[][]{
                {"insufficient_scope",
                        "The request requires higher privileges than provided by the access token"}
        };
    }
}
