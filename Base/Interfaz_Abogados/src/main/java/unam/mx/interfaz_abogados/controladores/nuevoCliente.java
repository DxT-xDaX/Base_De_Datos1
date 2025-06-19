package unam.mx.interfaz_abogados.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unam.mx.interfaz_abogados.conexion.Conexion;
import unam.mx.interfaz_abogados.tablas.Clientes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class nuevoCliente {

    @FXML private TextField TxTCurp;
    @FXML private TextField TxTNombre;
    @FXML private TextField TxTApellPat;
    @FXML private TextField TxTApellMat;
    @FXML private TextField TxTDire;
    @FXML private TextField TxTTel;
    @FXML private TextField TxTCorreo;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnLimpiar;
    private Clientes clienteSeleccionado;

    public void setCliente(Clientes clientes) {
        this.clienteSeleccionado = clientes;
        TxTCurp.setText(clientes.getCurp());
        TxTNombre.setText(clientes.getNombre());
        TxTApellPat.setText(clientes.getApellido_paterno());
        TxTApellMat.setText(clientes.getApellido_materno());
        TxTDire.setText(clientes.getDireccion());
        TxTTel.setText(clientes.getTelefono());
        TxTCorreo.setText(clientes.getCorreo());
        // etc.
    }

    public void eventoGuardarCliente() {
        if (clienteSeleccionado==null){
            try (Connection conn = new Conexion().getConexion()) {
                String sql = "INSERT INTO cliente (curp, nombre, apellido_paterno, apellido_materno, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, TxTCurp.getText());
                ps.setString(2, TxTNombre.getText());
                ps.setString(3, TxTApellPat.getText());
                ps.setString(4, TxTApellMat.getText());
                ps.setString(5, TxTDire.getText());
                ps.setString(6, TxTTel.getText());
                ps.setString(7, TxTCorreo.getText());
                // Añadir más campos
                ps.executeUpdate();
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Exito");
                alerta.setHeaderText("Exito");
                alerta.setContentText("Se guardo el cliente");
                alerta.showAndWait();
                cerrarVentana();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Problema en la BD");
                alerta.setHeaderText("Error en la aplicacion");
                alerta.setContentText("Consulta con el fabricante");
                alerta.showAndWait();
            }
        }
        else {
            try (Connection conn = new Conexion().getConexion()) {
                int id_cliente = clienteSeleccionado.getCliente_id();
                System.out.println("ID del cliente recibido: " + id_cliente); // Verifica que no sea 0
                String sql = "UPDATE cliente SET curp=?, nombre=?, apellido_paterno=?, apellido_materno=?, direccion=?, telefono=?, correo=? WHERE id_cte=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, TxTCurp.getText());
                ps.setString(2, TxTNombre.getText());
                ps.setString(3, TxTApellPat.getText());
                ps.setString(4, TxTApellMat.getText());
                ps.setString(5, TxTDire.getText());
                ps.setString(6, TxTTel.getText());
                ps.setString(7, TxTCorreo.getText());
                ps.setInt(8, clienteSeleccionado.getCliente_id());
                ps.executeUpdate();
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Exito");
                alerta.setHeaderText("Exito");
                alerta.setContentText("Se guardo el cliente");
                alerta.showAndWait();
                cerrarVentana();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Problema en la BD");
                alerta.setHeaderText("Error en la aplicacion");
                alerta.setContentText("Consulta con el fabricante");
                alerta.showAndWait();
            }
        }
    }

private void cerrarVentana() {
    Stage stage = (Stage) TxTNombre.getScene().getWindow();
    stage.close();
}

@FXML
private void eventoLimpiarCliente() {
    this.TxTCurp.setText("");
    this.TxTNombre.setText("");
    this.TxTApellPat.setText("");
    this.TxTApellMat.setText("");
    this.TxTDire.setText("");
    this.TxTTel.setText("");
    this.TxTCorreo.setText("");
}



}
