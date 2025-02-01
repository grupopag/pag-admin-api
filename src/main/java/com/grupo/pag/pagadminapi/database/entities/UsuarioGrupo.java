package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(initialValue = 1,allocationSize = 1,name = "usuario_grupo_permissao_id_seq",sequenceName = "usuario_grupo_permissao_id_seq")
public class UsuarioGrupo {

    public UsuarioGrupo(Grupo grupo , Usuario usuario){
        this.grupo = grupo;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(generator = "usuario_grupo_permissao_id_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
