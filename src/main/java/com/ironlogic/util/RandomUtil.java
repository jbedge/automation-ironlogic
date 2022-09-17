package com.ironlogic.util;

import com.ironlogic.base.TestConfiguration;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.UUID;

import static com.ironlogic.data.Constants.TEST_DATA;

public class RandomUtil {


    public static String getRandomNumber(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // first not 0 digit
        sb.append(random.nextInt(9) + 1);
        // rest of 11 digits
        for (int i = 1; i < size; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String getRandomString(int lenght) {
        String String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        StringBuilder out = new StringBuilder();
        Random rnd = new Random();
        while (out.length() < lenght) { // length of the random string.
            int index = (int) (rnd.nextFloat() * String.length());
            out.append(String.charAt(index));
        }
        String outStr = out.toString();
        return outStr;
    }

    public static String getRandomAlpahumeric(int lenght) {
        String alphanumeric= UUID.randomUUID().toString().replace("-", "");
//        StringBuilder out = new StringBuilder();
//        Random rnd = new Random();
//        while (out.length() < lenght) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * alphanumeric.length());
//            out.append(alphanumeric.charAt(index));
//        }
//        String outStr = out.toString();
        return alphanumeric.substring(0, lenght);
    }

    public static String getRandomPostalCode(int lenght) {
        StringBuilder out = new StringBuilder();
        Random rnd = new Random();
        while (out.length() < lenght) { // length of the random string.
            out.append(getRandomString(1));
            out.append(getRandomNumber(1));
        }
        String outStr = out.toString();
        return outStr;
    }

    public static String getRandom(String expInput, int lenght) {
        StringBuilder out = new StringBuilder();
        Random rnd = new Random();
        while (out.length() < lenght) { // length of the random string.
            int index = (int) (rnd.nextFloat() * expInput.length());
            out.append(expInput.charAt(index));
        }
        String outStr = out.toString();
        return outStr;
    }

    public static String getRandomPassword() {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = uppercase.toLowerCase();
        String numbers = "1234567890";
        String splCharacters = "!@#$%^&*()?<>";
        StringBuilder out = new StringBuilder();
        out.append(getRandom(uppercase, 3))
                .append(getRandom(numbers, 2))
                .append(getRandom(lowercase, 3))
                .append(getRandom(splCharacters, 2));
        String outStr = out.toString();
        return outStr;
    }

    public static void main(String[] args) {
      String alphanumeric= UUID.randomUUID().toString().replace("-", "");
        System.out.println(alphanumeric.substring(0, 6));
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }
    public static void dumpRuntimeData(TestConfiguration config) {
        try {
            File file = new File(TEST_DATA);
            FileWriter outputfile = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] headers = new String[]{config.toString()};
            writer.writeNext( headers);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
