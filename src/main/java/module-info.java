module com.mycompany.sistema_venta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.mycompany.sistema_venta to javafx.fxml;
    exports com.mycompany.sistema_venta;
}
