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

/**
 * Controller-Klasse steuert Benutzerinteraktionen und Datenverarbeitung
 */
public class Controller implements Initializable {

    @FXML private MenuBar menuBar;
    @FXML private Menu menuFile;
    @FXML private MenuItem miOpen;
    @FXML private MenuItem miQuit;
    @FXML private PieChart pieChart;

    private Model model;
    private final Stage stage;   // View

    /**
     * Konstruktor, der Stage referenziert
     *
     * @param stage Hauptbühne der Anwendung
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialisiert Controller-Klasse und setzt Event-Handler für Menü-Elemente
     *
     * @param url URL der Initialisierungsdatei
     * @param resourceBundle Ressourcenbündel
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        miOpen.setOnAction(this::miOpenAction);
        miQuit.setOnAction(this::miQuitAction);
    }

    /**
     * Verknüpft Model mit Controller und bindet Daten an PieChart
     *
     * @param m das zu verwendende Datenmodell
     */
    private void setModel(Model m) {
        model = m;
        StringProperty title = model.getTitleProperty();
        // Verbindet den Titel des PieCharts mit dem Titel aus dem Model
        pieChart.titleProperty().bind(title);

        final ObservableMap<String, Double> data = model.getData();
        // Listener wird hinzugefügt, um Änderungen im Datenmodell zu aktualisieren
        data.addListener(
                (MapChangeListener.Change<? extends String, ? extends Double> change) ->
                        updateStage(data)
        );
        updateStage(data);
    }

    /**
     * Aktualisiert das PieChart mit den aktuellen Daten aus dem Modell
     *
     * @param data Daten aus dem Modell
     */
    private void updateStage(ObservableMap<String, Double> data) {
        final ObservableList<PieChart.Data> chartdata = FXCollections.observableArrayList();
        double sum = 0.0;
        for (double d : data.values()) sum += d; // Summe der Daten berechnen
        double fsum = sum;
        // Daten dem PieChart hinzufügen
        data.forEach((s, d) -> chartdata.add(new PieChart.Data(s, d / fsum)));
        pieChart.setData(chartdata);
    }

    /**
     * Event-Handler für das Öffnen einer neuen PieChart-Datei
     *
     * @param event das auslösende Ereignis
     */
    private void miOpenAction(final ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Pie Chart");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pie Charts", "*.pie"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File f = fc.showOpenDialog(stage);
        try {
            if (f != null) {
                setModel(new Model(f.toPath())); // Model mit der ausgewählten Datei erstellen
            }
        } catch (Exception e) {
            Platform.exit();
        }
    }

    /**
     * Event-Handler für das Beenden der Anwendung
     *
     * @param event das auslösende Ereignis
     */
    private void miQuitAction(final ActionEvent event) {
        Platform.exit();
    }
}