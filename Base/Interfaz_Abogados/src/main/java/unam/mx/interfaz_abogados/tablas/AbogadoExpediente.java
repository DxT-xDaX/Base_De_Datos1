package unam.mx.interfaz_abogados.tablas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AbogadoExpediente {
        private final SimpleStringProperty abogado;
        private final SimpleStringProperty telefono;
        private final SimpleStringProperty situacion;
        private final SimpleStringProperty caso;

        public AbogadoExpediente(String abogado, String telefono, String situacion, String caso) {
            this.abogado = new SimpleStringProperty(abogado);
            this.telefono = new SimpleStringProperty(telefono);
            this.situacion = new SimpleStringProperty(situacion);
            this.caso = new SimpleStringProperty(caso);
        }

        public String getAbogado() { return abogado.get(); }
        public String getTelefono() { return telefono.get(); }
        public String getSituacion() { return situacion.get(); }
        public String getCaso() { return caso.get(); }

        // Property getters si los necesitas
    }

