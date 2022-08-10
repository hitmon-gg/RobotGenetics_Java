/**
 *
 * @author ianWallace, 2022
 */
package flc.cisp400.robotevolution;

import java.util.Random;
import java.util.Arrays;

public class Robot {
    Random randomInt = new Random();
    private final int numOfGenes = 16;
    private final int numOfProteins = 5;
    private int position;
    private int battery;
    private int turns;
    
    // Element 0 N, 1 S, 2 E, 3 W... 0 = empty, 1 = wall, 2 = battery
    private final int[] sensor = new int[4]; 
    private final DNA[] genes = new DNA[numOfGenes];
    
    // No - Arg Constructor
    public Robot() {
        position = randomInt.nextInt(100);
        battery  = 5;
        turns    = 0;

        for (int i = 0; i < numOfGenes; i++) {
            genes[i] = new DNA();
        }
    }
	
    // Baby Robot Constructor
    public Robot(Robot parent1, Robot parent2, int counter) {
        position = randomInt.nextInt(100);
        battery  = 5;
        turns    = 0;

        // Else combine genes from 2 robots into two new robots
        // Child 1
        if (counter % 2 == 0) {
            System.arraycopy(parent1.genes, 0, genes, 0, numOfGenes / 2);
            
            System.arraycopy(parent2.genes, numOfGenes / 2, genes, 
                    numOfGenes / 2, numOfGenes / 2);
        }

        //Child 2
        else {
            System.arraycopy(parent2.genes, 0, genes, 0, numOfGenes / 2);

            System.arraycopy(parent1.genes, numOfGenes / 2, genes, 
                    numOfGenes / 2, numOfGenes / 2);
        }

        // If mutation, swap genes around by 1;
        if (randomInt.nextInt() < 5){
            DNA tempGene;
            tempGene = genes[0];

            for (int i = 0; i < 14; i++) {
                    genes[i] = genes[i + 1];
            }

            genes[15] = tempGene;
        }
    }
    
    // Setters
    public void resetBattery()  {battery = 5;}
    public void resetTurns()    {turns = 0;}
    public void resetPosition() {position = randomInt.nextInt(100);}

    private void addPower() {battery += 5;} // When Robot finds a battery
    
    // Getters
    public int getBattery()  {return battery;}
    public int getTurns()    {return turns;}


    // Robot map traversing sensors
    private void senseArea(Map mapObj) {
        // Check for walls, true = 1
        // Check for batteries, true = 2;

        // North
        if (position - 10 < 0) {
            sensor[0] = 1;
        }

        else if (mapObj.batteryArray(position - 10) == 'b') {
            sensor[0] = 2;
        }
        
        // South
        if (position + 10 > 99) {
            sensor[1] = 1;
        }

        else if (mapObj.batteryArray(position + 10) == 'b') {
            sensor[1] = 2;
        }

        // East
        if ((position + 1) % 10 == 0 || position == 9) {
            sensor[2] = 1;
        }

        else if (mapObj.batteryArray(position + 1) == 'b') {
            sensor[2] = 2;
        }

        // West
        if (position % 10 == 0) {
            sensor[3] = 1;
        }

        else if (mapObj.batteryArray(position - 1) == 'b') {
            sensor[3] = 2;
        }
    }

    // Robot move logic
    public void move(Map mapObj) {
        int geneMatch;
        int newPosition = position;
        int direction = 0;
        
        turns++; // Increment every move
        senseArea(mapObj); // Determine robot surroundings
        
        // check genes for match with surroundings to determine direction to move
        for (int i = 0; i < numOfGenes; i++) {
            geneMatch = 0;

            for (int j = 0; j < (numOfProteins - 1); j++) {
                if (genes[i].proteins[j] == sensor[j] || genes[i].proteins[j] == 3) // 3 don't care
                    geneMatch++;
            }
            
            if (geneMatch == 4) {
                direction = genes[i].proteins[4];
                break;
            }
            
            if (i == 15) {
                direction = genes[15].proteins[4];
            }
        }

        if (direction == 4) {
            direction = randomInt.nextInt(4);
        }
        
        switch (direction) {
            case 0: // N
                if (sensor[0] != 1)
                    newPosition = position - 10;
                break;

            case 1: // S
                if (sensor[1] != 1)
                    newPosition = position + 10;
                break;

            case 2: // E
                if (sensor[2] != 1)
                    newPosition = position + 1;
                break;

            case 3: // W
                if (sensor[3] != 1)
                    newPosition = position - 1;
                break;

            default:

                break;
        }

        position = newPosition;
        
        if (mapObj.batteryArray(position) != 'b') {
            battery--;
        }
        
        else {
            mapObj.removeBattery(position);
            addPower();
        }
    }
}
