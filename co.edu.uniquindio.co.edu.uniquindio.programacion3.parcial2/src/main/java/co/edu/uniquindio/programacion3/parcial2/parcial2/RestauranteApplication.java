package co.edu.uniquindio.programacion3.parcial2.parcial2;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RestauranteApplication extends Application {
    private static Restaurante restaurante;

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializar el restaurante
        restaurante = new Restaurante();
        restaurante.cargarDatosIniciales();

        // Cargar la ventana de login
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login - Sistema Restaurante");
        stage.setScene(scene);
        stage.show();
    }

    public static Restaurante getRestaurante() {
        return restaurante;
    }

    public static void main(String[] args) {
        launch();
    }
}