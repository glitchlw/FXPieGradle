package de.medieninformatik.fxpiegradle;


import de.medieninformatik.fxpiegradle.model.Model;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private MenuBar menuBar;
    @FXML private Menu menuFile;
    @FXML private MenuItem miOpen;
    @FXML private MenuItem miQuit;
    @FXML private PieChart pieChart;

    private Model model;
    private final Stage stage;   // View

    public Controller(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miOpen.setOnAction(this::miOpenAction);
        miQuit.setOnAction(this::miQuitAction);
    }

    private void setModel(Model m) {
        model = m;
        StringProperty title = model.getTitleProperty();
        pieChart.titleProperty().bind(title);
        final ObservableMap<String, Double> data = model.getData();
        data.addListener(
                (MapChangeListener.Change<? extends String, ? extends Double> change) ->
                        updateStage(data)
        );
        updateStage(data);
    }

    private void updateStage(ObservableMap<String,Double> data) {
        final ObservableList<PieChart.Data> chartdata = FXCollections.observableArrayList();
        double sum = 0.0;
        for(double d : data.values()) sum += d;
        double fsum = sum;
        data.forEach((s, d) -> chartdata.add(new PieChart.Data(s, d/fsum)));
        pieChart.setData(chartdata);
    }

    private void miOpenAction(final ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Pie Chart");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pie Charts", "*.pie"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File f = fc.showOpenDialog(stage);
        try {
            if(f != null) {
                setModel(new Model(f.toPath()));
            }
        } catch(Exception e) {
            Platform.exit();
        }
    }

    private void miQuitAction(final ActionEvent event) {
        Platform.exit();
    }

}
