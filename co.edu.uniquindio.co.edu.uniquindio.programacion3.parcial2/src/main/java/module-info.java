module co.edu.uniquindio.programacion3.parcial2.parcial2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens co.edu.uniquindio.programacion3.parcial2.parcial2 to javafx.fxml;
    exports co.edu.uniquindio.programacion3.parcial2.parcial2;
    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Controller;
    opens co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Controller to javafx.fxml;

    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model;
    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Model;
    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Controller;
    opens co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Controller to javafx.fxml;

    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model;
    exports co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Controller;
    opens co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Controller to javafx.fxml;
}