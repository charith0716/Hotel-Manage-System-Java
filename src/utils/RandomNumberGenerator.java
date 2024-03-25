package utils;

import java.util.Random;

public class RandomNumberGenerator {
    public  int generate() {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random integer
        int randomNumber = random.nextInt();

        return randomNumber;
    }
}
