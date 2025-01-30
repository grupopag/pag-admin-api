package com.grupo.pag.pagadminapi.database.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estabelecimento", schema="pag_restaurante")
@SequenceGenerator(name = "estabelecimento_estabelecimento_id_seq",sequenceName = "estabelecimento_id_seq")
public class Estabelecimento {


    @Id
    @GeneratedValue(generator = "estabelecimento_id_seq")
    @Column(name = "estabelecimento_id")
    private Integer id;
    private String nome;
    private String cnpj;
    private String cpfResponsavel;
    private String cep;
    private Integer enteId;
    private String uf;
    private BigDecimal percentualServico;
    private BigDecimal valorCouvert;
    private Boolean ativarCouvert;
    private String chavePix;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @UpdateTimestamp
    private LocalDateTime ultimaAtualizacao;
    private String municipio;
    private String telefone;
    private String endereco;
    private String nomeResponsavel;

}
