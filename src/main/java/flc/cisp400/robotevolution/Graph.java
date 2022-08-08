/**
 *
 * @author ianWallace, 2022
 */
package flc.cisp400.robotevolution;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Graph {

    public static void printGraph (RobotData dataObj, int maxRobots) {
        float scoreFloat;
        float fitnessScoreAverage;
        int arraySize = dataObj.getArraySize();

        Stage stage = new Stage();
        stage.setTitle("Robot Evolution");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Robot Generations");

        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Robot Evolution");

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Robot Fitness Score Average");

        //populating the series with data
        for (int i = 0; i < arraySize; i++) {
            scoreFloat = dataObj.getRobotFitness(i);
            fitnessScoreAverage = scoreFloat / maxRobots;
            series.getData().add(new XYChart.Data((i + 1), fitnessScoreAverage));
        }

        Scene scene  = new Scene(lineChart,800,600);

        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }
}
