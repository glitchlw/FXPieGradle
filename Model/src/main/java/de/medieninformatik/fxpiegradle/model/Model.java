package de.medieninformatik.fxpiegradle.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Model-Klasse, die Daten und Titel für PieChart bereitstellt
 */
public class Model {

    private StringProperty title; // Titel des PieCharts
    private ObservableMap<String, Double> data; // Daten des PieCharts

    /**
     * Konstruktor, der Modell aus Datei initialisiert
     *
     * @param path Pfad zur Datei, die Daten für Modell enthält.
     */
    public Model(Path path) {
        try {
            List<String> content = Files.readAllLines(path);
            title = new SimpleStringProperty(content.get(0));
            data = FXCollections.observableHashMap();
            for (int i = 1; i < content.size(); i++) {
                String line = content.get(i);
                StringTokenizer token = new StringTokenizer(line);
                String name = token.nextToken();
                double value = Double.valueOf(token.nextToken());
                data.put(name, value);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Fehlerbehandlung beim Lesen der Datei
        }
    }

    /**
     * Gibt Titel zurück
     *
     * @return Property, die Titel des PieCharts enthält.
     */
    public StringProperty getTitleProperty() {
        return title;
    }

    /**
     * Gibt Daten des PieCharts zurück.
     *
     * @return Ein ObservableMap, das die Daten des PieCharts enthält.
     */
    public ObservableMap<String, Double> getData() {
        return data;
    }
}