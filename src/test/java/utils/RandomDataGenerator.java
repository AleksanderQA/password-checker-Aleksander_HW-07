package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

    public static String generateRandomName(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerName = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerName;
    }
    public static String generateRandomPhoneNumber (){
        int length = 8;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generateCustomerPhone = RandomStringUtils.random(length,useLetters,useNumbers);
        return generateCustomerPhone;
    }
    public static String generateRandomComment(){
        int length = 100;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generateCustomerComment = RandomStringUtils.random(length,useLetters,useNumbers);
        return generateCustomerComment;
    }
}
