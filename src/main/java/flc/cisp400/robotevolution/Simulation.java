/**
 *
 * @author ianda
 */

package flc.cisp400.robotevolution;
import java.util.*;
import java.security.SecureRandom;

public class Simulation {
    private final int maxSimulations;
    private final int halfMax;
    private final int maxRobots;

    private final SecureRandom randomInt = new SecureRandom();
    private final ArrayList<Robot> robotArray = new ArrayList<>();
    private final RobotData dataObj = new RobotData();

    // Constructor
    public Simulation(int maxSimulations, int maxRobots) {
        this.maxSimulations = maxSimulations;
        this.maxRobots = maxRobots;
        halfMax = maxRobots / 2;
        
        robotInitialization();
    }

    public void simLoop() {
        for (int i = 0; i < maxSimulations; i++) {
            robotTesting();
            dataObj.setFitnessScore();
            cullAndCreate();
            resetRobots();
        }
    }

    public void printData() {
        // Output as JavaFX line chart
        Graph.printGraph(dataObj, maxRobots);
    } 
    
    private void robotInitialization() {
        for (int i = 0; i < maxRobots; i++) {
            // Create new robot
            Robot robotObj = new Robot();
            
            // Place in array
            robotArray.add(robotObj);
        }
    }
    
    private void robotTesting() {
        dataObj.resetAccumulator();
        // create robot, run each through map, loop c_maxSimulation times for c_maxRobots robots
        for (int i = 0; i < maxRobots; i++) {
            Map mapObj = new Map();
            
            do {
                robotArray.get(i).move(mapObj);
                
            } while (robotArray.get(i).getBattery() > 0);
            
            dataObj.setAccumulator(robotArray.get(i).getTurns() - 5);
        }


    }
    
    private void cullAndCreate() {

        // Sort robotArray in descending order
        Collections.sort(robotArray, Robot.TurnsComparator);

        // Cull and create new robots from elements half to max (upper half)
        for (int i = 0; i <= halfMax; i += 2) {
            if (i == 0) {
                Robot newRobot1 = new Robot(robotArray.get(maxRobots - 1), robotArray.get(maxRobots - 2), i);
                Robot newRobot2 = new Robot(robotArray.get(maxRobots - 1), robotArray.get(maxRobots - 2), (i + 1));
                
                robotArray.add(newRobot1);
                robotArray.add(newRobot2);
            }
            
            else {
                Robot newRobot1 = new Robot(robotArray.get(maxRobots - i), robotArray.get(maxRobots - (i + 1)), i);
                Robot newRobot2 = new Robot(robotArray.get(maxRobots - i), robotArray.get(maxRobots - (i + 1)), (i + 1));
                
                robotArray.add(i, newRobot1);
                robotArray.add((i + 1), newRobot2);
            }
        }
    }
    
    private void resetRobots() {
        for (int i = halfMax; i < maxRobots; i++) {            
            robotArray.get(i).setBattery(5);
            robotArray.get(i).setTurns(0);
            robotArray.get(i).setPosition(randomInt.nextInt(100));
        }
    }
}
