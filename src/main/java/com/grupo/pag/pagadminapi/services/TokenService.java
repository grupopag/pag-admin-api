package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.database.entities.TokenUsuario;
import com.grupo.pag.pagadminapi.database.entities.Usuario;
import com.grupo.pag.pagadminapi.database.repository.TokenUsuarioRepository;
import com.grupo.pag.pagadminapi.database.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenUsuarioRepository tokenUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public TokenUsuario findTokenByToken(String token) {
        TokenUsuario tokenUsuario = tokenUsuarioRepository.findByToken(token);

        if (tokenUsuario != null) {
            Usuario usuario = usuarioRepository.findById(tokenUsuario.getUsuario().getUsuarioId()).get();

            usuario.setAtivo(true);
            usuarioRepository.save(usuario);
            tokenUsuario.setUtilizado(true);
            return tokenUsuarioRepository.save(tokenUsuario);
        }
        return null;
    }

}
