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

public class Model {
    private StringProperty title;
    private ObservableMap<String, Double> data;

    public Model(Path path) {
        try {
            List<String> content = Files.readAllLines(path);
            title = new SimpleStringProperty(content.get(0));
            data = FXCollections.observableHashMap();
            for(int i=1; i<content.size(); i++) {
                String line = content.get(i);
                StringTokenizer token = new StringTokenizer(line);
                String name = token.nextToken();
                double value = Double.valueOf(token.nextToken());
                data.put(name, value);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public StringProperty getTitleProperty() {
        return title;
    }

    public ObservableMap<String, Double> getData() {
        return data;
    }
}

