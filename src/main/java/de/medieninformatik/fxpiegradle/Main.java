package de.medieninformatik.fxpiegradle;

import javafx.application.Application;

/**
 * @author Luk Weiser, m30971
 * @author Tabea Sudrow, m30902
 * date: 2024-09-23
 * Programmierung 3, Aufgabe 1
 * Beschreibung der Klasse:
 * Hauptklasse für die Anwendung "Pie-Chart"
 */
public class Main {

    /**
     * Main-Methode, die das Programm startet.
     *
     * @param args Kommandozeilenargumente (nicht genutzt)
     */
    public static void main(String[] args) {
        // Startet JavaFX-Anwendung und ruft View-Klasse auf
        Application.launch(View.class, args);
    }
}