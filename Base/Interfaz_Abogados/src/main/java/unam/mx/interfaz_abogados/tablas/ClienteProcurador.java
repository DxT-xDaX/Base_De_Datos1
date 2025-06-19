package unam.mx.interfaz_abogados.tablas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteProcurador {
    private final SimpleStringProperty cliente;
    private final SimpleStringProperty caso;
    private final SimpleStringProperty procurador;

    public ClienteProcurador(String cliente, String caso, String procurador) {
        this.cliente = new SimpleStringProperty(cliente);
        this.caso = new SimpleStringProperty(caso);
        this.procurador = new SimpleStringProperty(procurador);
    }

    public String getCliente() { return cliente.get(); }
    public String getCaso() { return caso.get(); }
    public String getProcurador() { return procurador.get(); }

    // Property getters si los necesitas
}

