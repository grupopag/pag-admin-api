package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.EntidadeNaoEncontradaException;
import com.grupo.pag.pagadminapi.database.entities.Estabelecimento;
import com.grupo.pag.pagadminapi.database.repository.EstabelecimentoRepository;
import com.grupo.pag.pagadminapi.requests.EstabelecimentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    @Value("${estabelecimento.id}")
    private Long estabelecimentoId;

    public List<Estabelecimento> findAll() {
        return repository.findAll();
    }

    public Estabelecimento findByUsuarioLogado() {
        return repository.findById(estabelecimentoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Estabelecimento não encontrado"));

    }

    public Estabelecimento findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Estabelecimento não encontrado"));
    }

    public Estabelecimento save(EstabelecimentoRequest request) {
        Estabelecimento estabelecimento = new Estabelecimento();
        convertRequestToEntity(request, estabelecimento);
        return repository.save(estabelecimento);
    }

    public Estabelecimento update(Long id, EstabelecimentoRequest request) {
        return repository.findById(id).map(estabelecimento -> {
            convertRequestToEntity(request, estabelecimento);
            return repository.save(estabelecimento);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Estabelecimento não encontrado"));
    }

    public void delete(Long id) {
        Estabelecimento estabelecimento = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Estabelecimento não encontrado"));
        repository.delete(estabelecimento);
    }

    private void convertRequestToEntity(EstabelecimentoRequest request, Estabelecimento estabelecimento) {
        estabelecimento.setNome(request.getNome());
        estabelecimento.setCnpj(request.getCnpj());
        estabelecimento.setCpfResponsavel(request.getCpfResponsavel());
        estabelecimento.setCep(request.getCep());
        estabelecimento.setEnteId(request.getEnteId());
        estabelecimento.setUf(request.getUf());
        estabelecimento.setPercentualServico(request.getPercentualServico());
        estabelecimento.setValorCouvert(request.getValorCouvert());
        estabelecimento.setAtivarCouvert(request.getAtivarCouvert());
        estabelecimento.setMunicipio(request.getMunicipio());
        estabelecimento.setTelefone(request.getTelefone());
        estabelecimento.setEndereco(request.getEndereco());
        estabelecimento.setNomeResponsavel(request.getNomeResponsavel());
    }
}