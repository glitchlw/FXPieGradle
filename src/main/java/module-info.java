module de.medieninformatik.FXPieGradle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires de.medieninformatik.FXPieGradle.Model;


    opens de.medieninformatik.fxpiegradle to javafx.fxml;
    exports de.medieninformatik.fxpiegradle;
}