package com.grupo.pag.pagadminapi.database.repository;

import com.grupo.pag.pagadminapi.database.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
