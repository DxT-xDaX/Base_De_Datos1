package unam.mx.interfaz_abogados.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/gabinete_abogados?serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "axelym2410";

    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
