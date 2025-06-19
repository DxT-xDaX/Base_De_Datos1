package unam.mx.interfaz_abogados.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import unam.mx.interfaz_abogados.conexion.Conexion;
import unam.mx.interfaz_abogados.tablas.AbogadoExpediente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ControladorAbogadoExpediente {

    @FXML private TableView<AbogadoExpediente> tablaVista;
    @FXML private TableColumn<AbogadoExpediente, String> colAbogado;
    @FXML private TableColumn<AbogadoExpediente, String> colTelefono;
    @FXML private TableColumn<AbogadoExpediente, String> colSituacion;
    @FXML private TableColumn<AbogadoExpediente, String> colCaso;

    private final ObservableList<AbogadoExpediente> datosVista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colAbogado.setCellValueFactory(new PropertyValueFactory<>("abogado"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colSituacion.setCellValueFactory(new PropertyValueFactory<>("situacion"));
        colCaso.setCellValueFactory(new PropertyValueFactory<>("caso"));

        cargarVista();
    }

    private void cargarVista() {
        try (Connection conn = new Conexion().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vista_abogado_expediente")) {

            while (rs.next()) {
                datosVista.add(new AbogadoExpediente(
                        rs.getString("abogado"),
                        rs.getString("telefono"),
                        rs.getString("situacion"),
                        rs.getString("caso")
                ));
            }
            tablaVista.setItems(datosVista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
