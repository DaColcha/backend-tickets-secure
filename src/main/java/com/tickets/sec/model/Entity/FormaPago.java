package com.tickets.sec.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "forma_pago")
public class FormaPago {
    @Id
    @ColumnDefault("nextval('forma_pago_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "forma_pago", nullable = false, length = 50)
    private String formaPago;

    @OneToMany(mappedBy = "formapago")
    private Set<Pago> pagos = new LinkedHashSet<>();

    public FormaPago() {
    }

    public FormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}