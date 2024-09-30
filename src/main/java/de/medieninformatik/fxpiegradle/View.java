package de.medieninformatik.fxpiegradle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * View-Klasse; stellt Benutzeroberfläche bereit
 */
public class View extends Application {

    /**
     * Startet JavaFX-Anwendung und initialisiert "Stage".
     *
     * @param stage "Hauptbühne" der Anwendung
     * @throws Exception bei Problemen beim Laden der FXML-Datei
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Lädt fxml-Datei und initialisiert Controller-Klasse
        final URL fxmlURL = this.getClass().getResource("simple.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        fxmlLoader.setController(new Controller(stage));
        final Parent root = fxmlLoader.load();

        // Setze den Titel des Anwendungsfensters und zeige die Szene an
        stage.setTitle("PieChart Viewer");
        stage.setScene(new Scene(root));
        stage.show();
    }
}