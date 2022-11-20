package Application.Entities.Generators;

import java.util.Random;

public class NumGenerator {

    public static String generateFixLengthNumber(int length) {

        length = Math.abs(length);
        Random random = new Random();

        String randomValue = String.valueOf(random.nextDouble()).substring(2);
        String value = "";
        int maxLength = randomValue.length();

        int diff = length - maxLength;
        if (diff > 0) {

            length = maxLength;
            value += generateFixLengthNumber(diff);
        }

        value = randomValue.substring(0, length) + value;
        return value;

    }

}
