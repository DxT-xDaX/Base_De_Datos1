package unam.mx.interfaz_abogados.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorMenu {

    public void verCliente(ActionEvent event) throws IOException {
        cargarVentana("CRUD_Cliente.fxml", "Clientes");
    }

    public void verAbogado(ActionEvent event) throws IOException {
        cargarVentana("CRUD_Abogado.fxml", "Abogados");
    }

    @FXML
    private void abrirVistaAbogadoExpediente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/AbogadoExpediente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Vista Abogado-Expediente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirVistaClienteProcurador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/ClienteProcurador.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Vista Cliente-Procurador");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cerrar(ActionEvent event) {
        System.exit(0);
    }

    private void cargarVentana(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/unam/mx/interfaz_abogados/" + fxml));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
