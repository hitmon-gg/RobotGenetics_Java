
/**
 *
 * @author ianWallace, 2022
 */
package flc.cisp400.robotevolution;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root =
                FXMLLoader.load(getClass().getResource("SimulationController.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Robot Evolution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}