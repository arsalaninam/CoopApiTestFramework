package org.coop.util;

import org.testng.asserts.SoftAssert;

import java.util.Objects;

/**
 * Singleton objects class
 */
public class ObjectFactory {

    private static SoftAssert softAssert;

    private ObjectFactory() {
    }

    /**
     * If SoftAssert Class instance is not created, it creates and returns the instance.
     * If SoftAssert CLass instance is already created, it returns the instance.
     *
     * @return softAssert - Singleton instance of SoftAssert Class
     */
    public static SoftAssert getSoftAssert() {
        if (Objects.isNull(softAssert)) {
            softAssert = new SoftAssert();
        }
        return softAssert;
    }
}