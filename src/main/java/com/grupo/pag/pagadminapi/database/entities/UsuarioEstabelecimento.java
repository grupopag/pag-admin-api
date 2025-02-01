package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(initialValue = 1,allocationSize =1,sequenceName = "usuario_estabelecimento_id_seq",name = "usuario_estabelecimento_id_seq")
public class UsuarioEstabelecimento {

     public UsuarioEstabelecimento(Usuario usuario , Estabelecimento estabelecimento){
        this.usuario = usuario;
        this.estabelecimento = estabelecimento;
    }

    @Id
    @GeneratedValue(generator = "usuario_estabelecimento_id_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;
}
