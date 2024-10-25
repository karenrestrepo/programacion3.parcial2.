package co.edu.uniquindio.programacion3.parcial2.parcial2;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.EmpresaSoftware;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmpresaSoftwareApplication extends Application {
    private static EmpresaSoftware empresaSoftware;

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializar el restaurante
        empresaSoftware = new EmpresaSoftware();
        empresaSoftware.cargarDatosIniciales();

        // Cargar la ventana de login
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login - Sistema Empresa Software");
        stage.setScene(scene);
        stage.show();
    }

    public static EmpresaSoftware getEmpresaSoftware() {
        return empresaSoftware;
    }

    public static void main(String[] args) {
        launch();
    }
}
