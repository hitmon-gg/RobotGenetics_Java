package flc.cisp400.robotevolution;

/**
 *
 * @author ianda
 */

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.math.BigInteger;

public class SimulationController {

    @FXML
    private TextField inputNumRobots;

    @FXML
    private TextField inputNumSimulations;
    
    @FXML
    private void startButtonPressed(ActionEvent event) {

        try {
            BigInteger maxRobots = new BigInteger(inputNumRobots.getText());
            BigInteger simulations = new BigInteger(inputNumSimulations.getText());

            runSimulation(simulations.intValue(), maxRobots.intValue());
        }

        catch (NumberFormatException ex) {
            inputNumRobots.setText("Enter Integer");
            inputNumRobots.selectAll();
            inputNumRobots.requestFocus();

            inputNumSimulations.setText("Enter Integer");
            inputNumSimulations.selectAll();
            inputNumSimulations.requestFocus();

        }
    }

    private void runSimulation(int simulations, int maxRobots) {
        Simulation simObj = new Simulation(simulations, maxRobots);

        // Perform Simulation
        simObj.simLoop();

        // Output results
        simObj.printData();
    }
}

