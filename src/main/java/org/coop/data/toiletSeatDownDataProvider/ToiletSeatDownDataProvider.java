package org.coop.data.toiletSeatDownDataProvider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for collect eggs action related test cases
 *
 * @author Arsalan Inam
 */
public class ToiletSeatDownDataProvider {

    @DataProvider
    public static Object[][] toiletSeatDownSuccessResponse() {
        return new Object[][]{
                {"toiletseat-down", true, "You just put the toilet seat down. You're a wonderful roommate!" +
                        "Yea, the toilet seat is already down... you slob!"}
        };
    }
}
