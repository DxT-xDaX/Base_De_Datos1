package unam.mx.interfaz_abogados.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import unam.mx.interfaz_abogados.conexion.Conexion;
import unam.mx.interfaz_abogados.tablas.Abogados;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class nuevoAbogado {

    @FXML private TextField TxTCedula;
    @FXML private TextField TxTNombre;
    @FXML private TextField TxTApellPat;
    @FXML private TextField TxTApellMat;
    @FXML private TextField TxTCurp;
    @FXML private TextField TxTTel;
    @FXML private TextField TxTCorreo;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnLimpiar;
    private Abogados abogadoSeleccionado;

    public void setAbogado(Abogados abogados) {
        this.abogadoSeleccionado = abogados;
        TxTCedula.setText(abogados.getCedula());
        TxTNombre.setText(abogados.getNombre());
        TxTApellPat.setText(abogados.getApellido_paterno());
        TxTApellMat.setText(abogados.getApellido_materno());
        TxTCurp.setText(abogados.getCurp());
        TxTTel.setText(abogados.getTelefono());
        TxTCorreo.setText(abogados.getCorreo());
        // etc.
    }

    public void eventoGuardarAbogado() {
        if (abogadoSeleccionado==null){
            try (Connection conn = new Conexion().getConexion()) {
                String sql = "INSERT INTO abogado (cedula_profesional, nombre, apellido_paterno, apellido_materno, curp, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, TxTCedula.getText());
                ps.setString(2, TxTNombre.getText());
                ps.setString(3, TxTApellPat.getText());
                ps.setString(4, TxTApellMat.getText());
                ps.setString(5, TxTCurp.getText());
                ps.setString(6, TxTTel.getText());
                ps.setString(7, TxTCorreo.getText());
                // Añadir más campos
                ps.executeUpdate();
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Exito");
                alerta.setHeaderText("Exito");
                alerta.setContentText("Se guardo el abogado");
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
                int abogado_id = abogadoSeleccionado.getAbogado_id();
                System.out.println("ID del abogado recibido: " + abogado_id); // Verifica que no sea 0
                String sql = "UPDATE abogado SET cedula_profesional=?, nombre=?, apellido_paterno=?, apellido_materno=?, curp=?, telefono=?, correo=? WHERE id_abo=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, TxTCedula.getText());
                ps.setString(2, TxTNombre.getText());
                ps.setString(3, TxTApellPat.getText());
                ps.setString(4, TxTApellMat.getText());
                ps.setString(5, TxTCurp.getText());
                ps.setString(6, TxTTel.getText());
                ps.setString(7, TxTCorreo.getText());
                ps.setInt(8, abogadoSeleccionado.getAbogado_id());
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
    private void eventoLimpiarAbogado() {
        this.TxTCedula.setText("");
        this.TxTNombre.setText("");
        this.TxTApellPat.setText("");
        this.TxTApellMat.setText("");
        this.TxTCurp.setText("");
        this.TxTTel.setText("");
        this.TxTCorreo.setText("");
    }

    public boolean validarAbogado(String curp) {
        try (Connection conn = new Conexion().getConexion()) {
            CallableStatement cs = conn.prepareCall("{call validar_curp_unico(?, ?)}");
            cs.setString(1, curp);
            cs.registerOutParameter(2, Types.BOOLEAN);
            cs.execute();
            return cs.getBoolean(2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
