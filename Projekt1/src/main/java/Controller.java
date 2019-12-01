import RootFinders.*;
import Utils.Coordinates;
import Utils.Planet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    public TextField distance;
    @FXML
    public TextField eccentricity;
    @FXML
    public TextField revolution;
    @FXML
    public TextField planetName;
    @FXML
    public ScatterChart<NumberAxis, NumberAxis> lineChart;
    @FXML
    public ComboBox<RootFinderType> rootFinderCB ;
    public TableView<Planet> planetTable;
    public TableColumn<String, Planet> nameColumn;
    public TableColumn<String, Planet> distanceColumn;
    public TableColumn<String, Planet> revolutionColumn;
    public TableColumn<String, Planet> eccentrityColumn;
    public TableColumn<String, Planet> rootFinderColumn;


    private ArrayList<Planet> planets;

    public void createPlanetClick(ActionEvent actionEvent) {
        Planet planet = new Planet(
                planetName.getText(),
                Double.parseDouble(distance.getText()),
                Double.parseDouble(revolution.getText()),
                Double.parseDouble(eccentricity.getText()),
                getSelectedRootFinder());
        planets.add(planet);
        planetTable.getItems().add(planet);
    }

    public Controller() {
        planets = new ArrayList<>();
    }

    @FXML
    public void initialize(){
        rootFinderCB.getItems().setAll(RootFinderType.values());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        revolutionColumn.setCellValueFactory(new PropertyValueFactory<>("revolution"));
        eccentrityColumn.setCellValueFactory(new PropertyValueFactory<>("eccentricity"));
        rootFinderColumn.setCellValueFactory(new PropertyValueFactory<>("rootFinderName"));

    }

    private void generateChart(){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);
        lineChart.getData().clear(); // clear graph

        for (Planet p:planets
             ) {
            XYChart.Series series = new XYChart.Series();

            for (Coordinates coord : p.getTrajectory())
                series.getData().add(new XYChart.Data(coord.getX(), coord.getY()));

            lineChart.getData().add(series);
        }

    }

    public IRootFinder getSelectedRootFinder(){
        RootFinderType type = rootFinderCB.getValue();
        switch (type){
            case BISECTION:
                return new Bisection();
            case NEWTON:
                return new Newton();
            case REGULAFALSI:
                return new RegulaFalsi();
            case FIXEDPOINT:
                return new FixedPoint();
            case SECANT:
                return new Secant();
            default:
                return new Bisection();
        }
    }

    public void drawChart(ActionEvent actionEvent) {
        generateChart();
    }

    public void exportTrajectoriesClick(ActionEvent actionEvent) throws IOException {

        String filepath = "";
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(null);
        if (file != null) {
            filepath = file.toString();

        } else {
        }


        for (Planet p:planets
                ) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + "_" + p.getName() + ".csv"));
            writer.write(p.getPlanetTrajectoryCsv());
            writer.close();
        }
    }
}
