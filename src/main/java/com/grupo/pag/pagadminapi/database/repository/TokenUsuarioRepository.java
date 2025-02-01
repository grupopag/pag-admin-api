package com.grupo.pag.pagadminapi.database.repository;

import com.grupo.pag.pagadminapi.database.entities.TokenUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenUsuarioRepository extends JpaRepository<TokenUsuario, Long> {

    @Query("select t from TokenUsuario t where t.token = :token and t.utilizado = false")
    TokenUsuario findByToken(String token);


}
