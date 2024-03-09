package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

    public static String generateRandomName() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerName = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerName;
    }
    public static String generateRandomPhoneNumber() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerPhoneNumber = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerPhoneNumber;
    }
    public static String generateRandomComment() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerComment = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerComment;
    }
}
