import java.util.*;

public class PNR {

    static String generate() {

        int lowerLimit = 65;
        int upperLimit = 90;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(6);

        for (int i = 0; i < 6; i++) {

            // take a random value between 65 and 90
            int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char) nextRandomChar);
        }
        // return the resultant string
        return r.toString();
    }
}