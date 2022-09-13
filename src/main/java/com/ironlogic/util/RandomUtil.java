package com.ironlogic.util;

import java.util.Random;

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
        String String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder out = new StringBuilder();
        Random rnd = new Random();
        while (out.length() < lenght) { // length of the random string.
            int index = (int) (rnd.nextFloat() * String.length());
            out.append(String.charAt(index));
        }
        String outStr = out.toString();
        return outStr;
    }

    public static void main(String[] args) {

    }




}
