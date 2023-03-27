package org.coop.data.collectEggsDataProvider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for collect eggs action related test cases
 *
 * @author Arsalan Inam
 */
public class CollectEggsDataProvider {

    @DataProvider
    public static Object[][] collectEggsSuccessResponse() {
        return new Object[][]{
                {"eggs-collect", true}
        };
    }
}
