package com.grupo.pag.pagadminapi.database.repository;


import com.grupo.pag.pagadminapi.database.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

    @Query("select c from Categoria c where c.estabelecimentoId = :estabelecimentoId")
    List<Categoria> findByEstabelecimentoId(Long estabelecimentoId);

}
