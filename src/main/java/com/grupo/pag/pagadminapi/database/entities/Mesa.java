package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Table(name = "mesa")
@SequenceGenerator(sequenceName = "mesa_mesa_id_seq",name = "mesa_mesa_id_seq",allocationSize = 1,initialValue = 1)
public class Mesa implements Cloneable{

    @Id
    @GeneratedValue(generator = "mesa_mesa_id_seq")
    private Integer mesaId;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    private Boolean ativa;

    private Integer lugares;

    private Integer lugaresAdicionais;

    private Boolean permiteReserva;

    @CreationTimestamp
    private Date dataCadastro;

    private String numero;

    private String codigo;

    private Date dataEncerramento;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Usuario garcom;


    @Override
    public Mesa clone() {
        try {
            Mesa clone = (Mesa) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}
