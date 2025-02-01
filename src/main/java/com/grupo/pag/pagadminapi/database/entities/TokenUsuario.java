package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "token_usuario", schema = "pag_restaurante")
@Data
public class TokenUsuario {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Boolean utilizado = false;

    public boolean isUtilizado(){
        return utilizado;
    }

}
