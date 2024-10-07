package com.tickets.sec.model.Entity;

import java.io.Serializable;

public class AsientoId implements Serializable {
    private Integer num_asiento;
    private String localidad;
    private String zona;
    private String tipo;

    public AsientoId() {
    }

    public AsientoId(Integer num_asiento, String localidad, String zona, String tipo) {
        this.num_asiento = num_asiento;
        this.localidad = localidad;
        this.zona = zona;
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((num_asiento == null) ? 0 : num_asiento.hashCode());
        result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
        result = prime * result + ((zona == null) ? 0 : zona.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AsientoId other = (AsientoId) obj;
        if (num_asiento == null) {
            if (other.num_asiento != null)
                return false;
        } else if (!num_asiento.equals(other.num_asiento))
            return false;
        if (localidad == null) {
            if (other.localidad != null)
                return false;
        } else if (!localidad.equals(other.localidad))
            return false;
        if (zona == null) {
            if (other.zona != null)
                return false;
        } else if (!zona.equals(other.zona))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

}
