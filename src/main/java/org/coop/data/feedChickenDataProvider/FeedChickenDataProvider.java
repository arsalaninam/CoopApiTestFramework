package org.coop.data.feedChickenDataProvider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for collect eggs action related test cases
 *
 * @author Arsalan Inam
 */
public class FeedChickenDataProvider {

    @DataProvider
    public static Object[][] feedChickenSuccessResponse() {
        return new Object[][]{
                {"chickens-feed", true}
        };
    }
}
