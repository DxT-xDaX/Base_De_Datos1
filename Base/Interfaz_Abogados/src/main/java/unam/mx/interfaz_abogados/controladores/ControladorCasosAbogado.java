package unam.mx.interfaz_abogados.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import unam.mx.interfaz_abogados.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorCasosAbogado {

    @FXML private TextField txtIdAbogado;
    @FXML private TextField txtCasos;

    @FXML
    private void consultarCasos() {
        String idText = txtIdAbogado.getText();
        try {
            int idAbogado = Integer.parseInt(idText);
            int total = obtenerCasosVigentes(idAbogado);
            txtCasos.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número entero.");
        }
    }

    private int obtenerCasosVigentes(int idAbogado) {
        try (Connection conn = new Conexion().getConexion();
             PreparedStatement ps = conn.prepareStatement("SELECT contar_casos_abogado(?)")) {
            ps.setInt(1, idAbogado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error de BD", "No se pudo consultar los casos.");
        }
        return 0;
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    @FXML
    private void eventoLimpiarCasos() {
        this.txtIdAbogado.setText("");
        this.txtCasos.setText("");
    }

}
