package unam.mx.interfaz_abogados.controladores;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import unam.mx.interfaz_abogados.conexion.Conexion;
import unam.mx.interfaz_abogados.tablas.Clientes;

import java.io.IOException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unam.mx.interfaz_abogados.controladores.ControladorMenu;


public class Controlador_CRUD_Cliente {

    @FXML private TableView<Clientes> tableView;
    @FXML private TableColumn<Clientes, Integer> cliente_id;
    @FXML private TableColumn<Clientes, String> curp;
    @FXML private TableColumn<Clientes, String> nombre;
    @FXML private TableColumn<Clientes, String> apellido_paterno;
    @FXML private TableColumn<Clientes, String> apellido_materno;
    @FXML private TableColumn<Clientes, String> direccion;
    @FXML private TableColumn<Clientes, String> telefono;
    @FXML private TableColumn<Clientes, String> correo;
    @FXML private TableColumn<Clientes, Void> colEditar;
    @FXML private TableColumn<Clientes, Void> colEliminar;


    private ObservableList<Clientes> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // columnas existentes
        cliente_id.setCellValueFactory(data -> data.getValue().cliente_idProperty().asObject());
        curp.setCellValueFactory(data -> data.getValue().curpProperty());
        nombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        apellido_paterno.setCellValueFactory(data -> data.getValue().apellido_paternoProperty());
        apellido_materno.setCellValueFactory(data -> data.getValue().apellido_maternoProperty());
        direccion.setCellValueFactory(data -> data.getValue().direccionProperty());
        telefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        correo.setCellValueFactory(data -> data.getValue().correoProperty());

        // cargar datos y botones
        cargarClientes();
        agregarBotonesAccion();
    }

    private void agregarBotonesAccion() {
        colEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Eliminar");

            {
                btn.setOnAction(event -> {
                    Clientes clientes = getTableView().getItems().get(getIndex());
                    eliminarCliente(clientes);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        colEditar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Editar");

            {
                btn.setOnAction(event -> {
                    Clientes clientes = getTableView().getItems().get(getIndex());
                    mostrarFormularioEdicion(clientes);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }

    private void mostrarFormularioEdicion(Clientes clientes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/nuevoCliente.fxml"));
            Parent root = loader.load();

            nuevoCliente controlador = loader.getController();
            controlador.setCliente(clientes); // pasa cliente a editar

            Stage stage = new Stage();
            stage.setTitle("Editar Cliente");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            cargarClientes(); // recarga la tabla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void cargarClientes() {
        listaClientes.clear();

        try (Connection conn = new Conexion().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")) {

            while (rs.next()) {
                Clientes c = new Clientes(
                        rs.getInt("id_cte"),
                        rs.getString("curp"),
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                listaClientes.add(c);
            }
            tableView.setItems(listaClientes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(Clientes clientes) {
        try (Connection conn = new Conexion().getConexion();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM cliente WHERE id_cte = ?")) {
            ps.setInt(1, clientes.getCliente_id());
            ps.executeUpdate();
            listaClientes.remove(clientes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirNuevoCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/nuevoCliente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Cliente");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            cargarClientes(); // recarga la tabla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
