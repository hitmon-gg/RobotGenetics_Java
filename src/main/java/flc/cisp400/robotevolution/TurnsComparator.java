/**
 *
 * @author ianWallace, 2022
 */

package flc.cisp400.robotevolution;

import java.util.Comparator;

public class TurnsComparator implements Comparator<Robot> {
    @Override
    public int compare(Robot robot1, Robot robot2) {

        return robot1.getTurns() - robot2.getTurns();
    }
}
