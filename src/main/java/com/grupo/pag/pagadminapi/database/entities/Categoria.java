package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categoria" )
@SequenceGenerator(sequenceName = "categoria_categoria_id_seq" , name = "categoria_categoria_id_seq",initialValue = 1,allocationSize = 1)
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(generator = "categoria_categoria_id_seq")
    private Integer categoriaId;
    private String descricao;
    private String imagem;
    private Long estabelecimentoId;
    private Integer ordem;
}
