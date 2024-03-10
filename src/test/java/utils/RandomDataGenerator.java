package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {


    public static String generateName(){
        int length  = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerName = RandomStringUtils.random(length,useLetters,useNumbers);
        return generatedCustomerName;
    }

    public static String generatePhoneNumber(){
        int length = 8;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generateCustomerPhoneNumber = RandomStringUtils.random(length,useLetters,useNumbers);
        return generateCustomerPhoneNumber;
    }

    public static String generateComment(){
        int length  = 100;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generateComment = RandomStringUtils.random(length,useLetters,useNumbers);
        return generateComment;

    }
}
