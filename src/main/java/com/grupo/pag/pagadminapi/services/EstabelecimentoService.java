package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.BusinessException;
import com.grupo.pag.pagadminapi.database.entities.*;
import com.grupo.pag.pagadminapi.database.repository.*;
import com.grupo.pag.pagadminapi.requests.EstabelecimentoRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final TokenUsuarioRepository tokenUsuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final UsuarioEstabelecimentoRepository usuarioEstabelecimentoRepository;
    private final PasswordEncoder passwordEncoder;
    private final MesaService mesaService;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    @Transactional
    public Estabelecimento save(EstabelecimentoRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()) != null) {
            throw new BusinessException("Já existe um usuário com esse email");
        }

        Estabelecimento estabelecimento = new Estabelecimento();
        convertRequestToEntity(request, estabelecimento);
        Estabelecimento estabelecimentoSalvo = repository.save(estabelecimento);


        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNomeResponsavel());
        usuario.setLogin(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setAtivo(false);

        usuario = usuarioRepository.save(usuario);

        Grupo grupo = grupoRepository.findById(1).get();
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        usuarioGrupo.setUsuario(usuario);
        usuarioGrupo.setGrupo(grupo);
        usuarioGrupoRepository.save(usuarioGrupo);

        UsuarioEstabelecimento usuarioEstabelecimento = new UsuarioEstabelecimento();
        usuarioEstabelecimento.setUsuario(usuario);
        usuarioEstabelecimento.setEstabelecimento(estabelecimentoSalvo);

        usuarioEstabelecimentoRepository.save(usuarioEstabelecimento);

        String token = UUID.randomUUID().toString();


        TokenUsuario tokenUsuario = new TokenUsuario();
        tokenUsuario.setToken(token);
        tokenUsuario.setUsuario(usuario);


        tokenUsuarioRepository.save(tokenUsuario);

        mesaService.criarMesasParaEstabelecimento(estabelecimentoSalvo.getId(), 7);
        categoriaService.criarCategoriasParaEstabelecimento(estabelecimentoSalvo.getId());
        produtoService.criarProdutosParaEstabelecimento(estabelecimentoSalvo.getId());

        emailService.sendConfirmationEmail(request.getEmail(), request.getNomeResponsavel(),
                token
        );

        return estabelecimentoSalvo;

    }


    private void convertRequestToEntity(EstabelecimentoRequest request, Estabelecimento estabelecimento) {
        estabelecimento.setNome(request.getNome());
        estabelecimento.setCnpj(request.getCnpj());
        estabelecimento.setCpfResponsavel(request.getCpfResponsavel());
        estabelecimento.setCep(request.getCep());
        estabelecimento.setUf(request.getUf());
        estabelecimento.setPercentualServico(request.getPercentualServico());
        estabelecimento.setValorCouvert(request.getValorCouvert());
        estabelecimento.setAtivarCouvert(request.getAtivarCouvert());
        estabelecimento.setMunicipio(request.getMunicipio());
        estabelecimento.setTelefone(request.getTelefone());
        estabelecimento.setEndereco(request.getEndereco());
        estabelecimento.setNomeResponsavel(request.getNomeResponsavel());
        estabelecimento.setChavePix(request.getChavePix());
    }
}