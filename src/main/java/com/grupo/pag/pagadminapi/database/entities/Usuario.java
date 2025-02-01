package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "pag_restaurante")
@SequenceGenerator(name = "usuario_usuario_id_seq",sequenceName = "usuario_usuario_id_seq",allocationSize = 1,initialValue = 1)
public class Usuario {

    public Usuario(Integer id){
        this.usuarioId = id;
    }

    @Id
    @GeneratedValue(generator = "usuario_usuario_id_seq")
    @Column(name = "usuario_id")
    private Integer usuarioId;
    private String login;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;

    public void ativar() {
        this.ativo = true;
    }







}
