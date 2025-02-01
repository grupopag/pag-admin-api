package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
@Log4j2
@SequenceGenerator(sequenceName = "produto_produto_id_seq", name = "produto_produto_id_seq", allocationSize = 1, initialValue = 1)
public class Produto {

    public Produto(Long produtoId) {
        this.produtoId = produtoId;
    }


    @Id
    @GeneratedValue(generator = "produto_produto_id_seq")
    private Long produtoId;
    private String nome;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;
    private String codigo;
    private BigDecimal preco;
    private String imagem;
    private String imagem32;
    private String imagem64;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private Boolean ativo;
    @JoinColumn(name = "setor_id")
    @ManyToOne
    private Setor setor;

    private BigDecimal precoCusto;

    @UpdateTimestamp
    private LocalDateTime dataUltimaAtualizacao;


}
