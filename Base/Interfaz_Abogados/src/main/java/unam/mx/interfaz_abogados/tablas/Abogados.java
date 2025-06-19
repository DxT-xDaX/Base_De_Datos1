package unam.mx.interfaz_abogados.tablas;

import javafx.beans.property.*;

public class Abogados {
    private IntegerProperty abogado_id;
    private StringProperty cedula;
    private StringProperty nombre;
    private StringProperty apellido_paterno;
    private StringProperty apellido_materno;
    private StringProperty curp;
    private StringProperty telefono;
    private StringProperty correo;

    public Abogados(int abogado_id, String cedula, String nombre, String apePat, String apeMat, String curp, String telefono, String correo) {
        this.abogado_id= new SimpleIntegerProperty(abogado_id);
        this.cedula = new SimpleStringProperty(cedula);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido_paterno = new SimpleStringProperty(apePat);
        this.apellido_materno = new SimpleStringProperty(apeMat);
        this.curp = new SimpleStringProperty(curp);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
    }

    public int getAbogado_id() { return abogado_id.get(); }
    public IntegerProperty abogado_idProperty() { return abogado_id; }

    public String getCedula() { return cedula.get(); }
    public StringProperty cedulaProperty() { return cedula; }

    public String getNombre() { return nombre.get(); }
    public StringProperty nombreProperty() { return nombre; }

    public String getApellido_paterno() { return apellido_paterno.get(); }
    public StringProperty apellido_paternoProperty() { return apellido_paterno; }

    public String getApellido_materno() { return apellido_materno.get(); }
    public StringProperty apellido_maternoProperty() { return apellido_materno; }

    public String getCurp() { return curp.get(); }
    public StringProperty curpProperty() { return curp; }

    public String getTelefono() { return telefono.get(); }
    public StringProperty telefonoProperty() { return telefono; }

    public String getCorreo() { return correo.get(); }
    public StringProperty correoProperty() { return correo; }
}
