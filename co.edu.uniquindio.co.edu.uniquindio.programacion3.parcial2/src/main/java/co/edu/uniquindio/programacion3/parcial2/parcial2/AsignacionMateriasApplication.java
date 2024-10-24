package co.edu.uniquindio.programacion3.parcial2.parcial2;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.AsignacionMaterias;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AsignacionMateriasApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EstudianteApplication.class.getResource("Login4.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
