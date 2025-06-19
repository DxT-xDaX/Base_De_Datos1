package unam.mx.interfaz_abogados.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import unam.mx.interfaz_abogados.conexion.Conexion;
import unam.mx.interfaz_abogados.tablas.ClienteProcurador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ControladorClienteProcurador {

    @FXML private TableView<ClienteProcurador> tablaVista;
    @FXML private TableColumn<ClienteProcurador, String> colCliente;
    @FXML private TableColumn<ClienteProcurador, String> colCaso;
    @FXML private TableColumn<ClienteProcurador, String> colProcurador;

    private final ObservableList<ClienteProcurador> datosVista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCaso.setCellValueFactory(new PropertyValueFactory<>("caso"));
        colProcurador.setCellValueFactory(new PropertyValueFactory<>("procurador"));

        cargarVista();
    }

    private void cargarVista() {
        try (Connection conn = new Conexion().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vista_cliente_procurador")) {

            while (rs.next()) {
                datosVista.add(new ClienteProcurador(
                        rs.getString("cliente"),
                        rs.getString("caso"),
                        rs.getString("procurador")
                ));
            }
            tablaVista.setItems(datosVista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
