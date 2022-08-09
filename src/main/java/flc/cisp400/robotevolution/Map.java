/**
 *
 * @author ianWallace, 2022
 */
package flc.cisp400.robotevolution;

import java.security.SecureRandom;
import java.util.Arrays;

public class Map {
    // 'b' for battery space, 'o' for empty space
    private final char[] batteryArray = new char[100];  

    public Map() {
        final SecureRandom randomInt = new SecureRandom();
        final int SIZE = 100;
        final int MAX_BATTERIES = 40;
        int space;
        int counter = 0;

        Arrays.fill(batteryArray, 'o');

        // Place 40 batteries randomly
        do {
            space = randomInt.nextInt(100);

            if (batteryArray[space] == 'o') {
                    batteryArray[space] = 'b';
                    counter++;
            }

        } while (counter < MAX_BATTERIES);
    }

    // Interface
    public char batteryArray(int i) {
            return batteryArray[i];
    }

    public void removeBattery(int i) {
            batteryArray[i] = 'o';
    }
}
