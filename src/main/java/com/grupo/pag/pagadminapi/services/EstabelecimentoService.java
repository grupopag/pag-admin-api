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

    public Estabelecimento save(EstabelecimentoRequest request) {
        Estabelecimento estabelecimento = new Estabelecimento();
        convertRequestToEntity(request, estabelecimento);
        return repository.save(estabelecimento);
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
    }
}