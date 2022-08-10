/**
 *
 * @author ianWallace, 2022
 */

package flc.cisp400.robotevolution;
import java.util.Random;

public class DNA {
    // Element 0 N, 1 S, 2 E, 3 W, 4 Move
    // int 0 empty, 1 wall, 2 battery, 3 don't care
    private final int numOfProteins = 5;
    protected int[] proteins = new int[numOfProteins];
	Random randomInt = new Random();
    
    // no Arg Constructor
    public DNA() {
        // Randomize proteins N S E W
        for (int i = 0; i < (numOfProteins - 1); i++) {
                proteins[i] = randomInt.nextInt(4);
        }

        // Randomize protein Move
        proteins[4] = randomInt.nextInt(5);
    }
}
