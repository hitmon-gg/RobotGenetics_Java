/**
 *
 * @author ianWallace, 2022
 */
package flc.cisp400.robotevolution;
import java.util.ArrayList;

public class RobotData {
    private int accumulator;
    private final ArrayList<Integer> fitnessScore = new ArrayList<>();

    public void resetAccumulator() {accumulator = 0;}
    public void setFitnessScore() {fitnessScore.add(accumulator);}
    public void setAccumulator(int turns) {accumulator += turns;}
    public int  getRobotFitness(int i) {return fitnessScore.get(i);}   
    public int  getArraySize() {return fitnessScore.size();}
}

