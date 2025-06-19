module unam.mx.interfaz_abogados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.mariadb.jdbc;


    opens unam.mx.interfaz_abogados to javafx.fxml;
    exports unam.mx.interfaz_abogados;
    exports unam.mx.interfaz_abogados.conexion;
    opens unam.mx.interfaz_abogados.conexion to javafx.fxml;
    exports unam.mx.interfaz_abogados.tablas;
    opens unam.mx.interfaz_abogados.tablas to javafx.fxml;
    exports unam.mx.interfaz_abogados.controladores;
    opens unam.mx.interfaz_abogados.controladores to javafx.fxml;
}