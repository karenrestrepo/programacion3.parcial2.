package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Controller;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnIngresar;

    @FXML
    void onIngresar(ActionEvent event) {
        try {
            if (Persistencia.validarCredenciales(txtUsuario.getText(), txtContrasenia.getText())) {
                // Cargar la ventana de pedidos
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/programacion3/parcial2/parcial2/Pedidos.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Sistema de Pedidos");
                stage.setScene(new Scene(root));
                stage.show();

                // Cerrar ventana de login
                Stage loginStage = (Stage) btnIngresar.getScene().getWindow();
                loginStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de autenticación");
                alert.setHeaderText(null);
                alert.setContentText("Usuario o contraseña incorrectos");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al abrir la ventana de pedidos: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
