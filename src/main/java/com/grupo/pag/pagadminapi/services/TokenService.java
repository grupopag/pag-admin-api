package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.database.entities.TokenUsuario;
import com.grupo.pag.pagadminapi.database.repository.TokenUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenUsuarioRepository tokenUsuarioRepository;

    public TokenUsuario findTokenByToken(String token) {
        TokenUsuario tokenUsuario = tokenUsuarioRepository.findByToken(token);
        if (tokenUsuario != null) {
          tokenUsuario.setUtilizado(true);
          return tokenUsuarioRepository.save(tokenUsuario);
        }
        return null;
    }

}
