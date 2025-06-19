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
import unam.mx.interfaz_abogados.tablas.Abogados;

import java.io.IOException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unam.mx.interfaz_abogados.controladores.ControladorMenu;


public class Controlador_CRUD_Abogado {

    @FXML private TableView<Abogados> tableView;
    @FXML private TableColumn<Abogados, Integer> abogado_id;
    @FXML private TableColumn<Abogados, String> cedula;
    @FXML private TableColumn<Abogados, String> nombre;
    @FXML private TableColumn<Abogados, String> apellido_paterno;
    @FXML private TableColumn<Abogados, String> apellido_materno;
    @FXML private TableColumn<Abogados, String> curp;
    @FXML private TableColumn<Abogados, String> telefono;
    @FXML private TableColumn<Abogados, String> correo;
    @FXML private TableColumn<Abogados, Void> colEditar;
    @FXML private TableColumn<Abogados, Void> colEliminar;


    private ObservableList<Abogados> listaAbogados = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // columnas existentes
        abogado_id.setCellValueFactory(data -> data.getValue().abogado_idProperty().asObject());
        cedula.setCellValueFactory(data -> data.getValue().cedulaProperty());
        nombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        apellido_paterno.setCellValueFactory(data -> data.getValue().apellido_paternoProperty());
        apellido_materno.setCellValueFactory(data -> data.getValue().apellido_maternoProperty());
        curp.setCellValueFactory(data -> data.getValue().curpProperty());
        telefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        correo.setCellValueFactory(data -> data.getValue().correoProperty());

        // cargar datos y botones
        cargarAbogados();
        agregarBotonesAccion();
    }

    private void agregarBotonesAccion() {
        colEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Eliminar");

            {
                btn.setOnAction(event -> {
                    Abogados abogados = getTableView().getItems().get(getIndex());
                    eliminarAbogado(abogados);
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
                    Abogados abogados = getTableView().getItems().get(getIndex());
                    mostrarFormularioEdicion(abogados);
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

    private void mostrarFormularioEdicion(Abogados abogados) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/nuevoAbogado.fxml"));
            Parent root = loader.load();

            nuevoAbogado controlador = loader.getController();
            controlador.setAbogado(abogados); // pasa cliente a editar

            Stage stage = new Stage();
            stage.setTitle("Editar Abogado");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            cargarAbogados(); // recarga la tabla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void cargarAbogados() {
        listaAbogados.clear();

        try (Connection conn = new Conexion().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM abogado")) {

            while (rs.next()) {
                Abogados a = new Abogados(
                        rs.getInt("id_abo"),
                        rs.getString("cedula_profesional"),
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("curp"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                listaAbogados.add(a);
            }
            tableView.setItems(listaAbogados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAbogado(Abogados abogados) {
        try (Connection conn = new Conexion().getConexion();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM abogado WHERE id_abo = ?")) {
            ps.setInt(1, abogados.getAbogado_id());
            ps.executeUpdate();
            listaAbogados.remove(abogados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirNuevoAbogado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/nuevoAbogado.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Abogado");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            cargarAbogados(); // recarga la tabla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCasosAbogado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/CasosAbogado.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Casos Vigentes por Abogado");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
