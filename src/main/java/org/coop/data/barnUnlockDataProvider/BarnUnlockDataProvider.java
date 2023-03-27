package org.coop.data.barnUnlockDataProvider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for barn unlock action related test cases
 *
 * @author Arsalan Inam
 */
public class BarnUnlockDataProvider {

    @DataProvider
    public static Object[][] barnUnlockSuccessResponse() {
        return new Object[][]{
                {"barn-unlock", true,
                        "You just unlocked your barn! Watch out for strangers! " +
                                "The barn is already wide open! Let's throw a party!"}
        };
    }
}
