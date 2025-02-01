package com.grupo.pag.pagadminapi.database.repository;

import com.grupo.pag.pagadminapi.database.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {



}