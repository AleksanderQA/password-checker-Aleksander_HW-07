package utils;

import org.apache.commons.lang3.RandomStringUtils;
public class RandomObjectGenerator {

    public static String generateRandomCustomerName(){

            int length = 10;
            boolean useLetters = true;
            boolean useNumbers = false;
            String generatedCustomerName = RandomStringUtils.random(length, useLetters, useNumbers);

            return generatedCustomerName;
    }
    public static String generateRandomCustomerPhone() {

        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedCustomerPhone = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerPhone;
    }
    public static String generateRandomCustomerComment() {

        int length = 100;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedCustomerComment = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerComment;
    }
}
