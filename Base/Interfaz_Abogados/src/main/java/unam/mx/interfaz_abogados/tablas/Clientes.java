package unam.mx.interfaz_abogados.tablas;

import javafx.beans.property.*;

public class Clientes {
    private IntegerProperty cliente_id;
    private StringProperty curp;
    private StringProperty nombre;
    private StringProperty apellido_paterno;
    private StringProperty apellido_materno;
    private StringProperty direccion;
    private StringProperty telefono;
    private StringProperty correo;

    public Clientes(int cliente_id, String curp, String nombre, String apePat, String apeMat, String direccion, String telefono, String correo) {
        this.cliente_id = new SimpleIntegerProperty(cliente_id);
        this.curp = new SimpleStringProperty(curp);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido_paterno = new SimpleStringProperty(apePat);
        this.apellido_materno = new SimpleStringProperty(apeMat);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
    }

    public int getCliente_id() { return cliente_id.get(); }
    public IntegerProperty cliente_idProperty() { return cliente_id; }

    public String getCurp() { return curp.get(); }
    public StringProperty curpProperty() { return curp; }

    public String getNombre() { return nombre.get(); }
    public StringProperty nombreProperty() { return nombre; }

    public String getApellido_paterno() { return apellido_paterno.get(); }
    public StringProperty apellido_paternoProperty() { return apellido_paterno; }

    public String getApellido_materno() { return apellido_materno.get(); }
    public StringProperty apellido_maternoProperty() { return apellido_materno; }

    public String getDireccion() { return direccion.get(); }
    public StringProperty direccionProperty() { return direccion; }

    public String getTelefono() { return telefono.get(); }
    public StringProperty telefonoProperty() { return telefono; }

    public String getCorreo() { return correo.get(); }
    public StringProperty correoProperty() { return correo; }
}
