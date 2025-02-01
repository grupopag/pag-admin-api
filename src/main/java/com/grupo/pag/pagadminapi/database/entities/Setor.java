package com.grupo.pag.pagadminapi.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "setor")
@Data
public class Setor {
    @Id
    private Integer setorId;
    private String nome;
}
