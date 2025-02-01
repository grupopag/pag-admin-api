package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.database.entities.Estabelecimento;
import com.grupo.pag.pagadminapi.database.entities.Mesa;
import com.grupo.pag.pagadminapi.database.repository.EstabelecimentoRepository;
import com.grupo.pag.pagadminapi.database.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    @Transactional
    public List<Mesa> criarMesasParaEstabelecimento(Long estabelecimentoId, int quantidadeMesas) {
        // Busca o estabelecimento
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
            .orElseThrow(() -> new IllegalArgumentException("Estabelecimento não encontrado com ID: " + estabelecimentoId));

        List<Mesa> mesas = new ArrayList<>();

        for (int i = 1; i <= quantidadeMesas; i++) {
            Mesa mesa = new Mesa();
            mesa.setEstabelecimento(estabelecimento);
            mesa.setAtiva(true);
            mesa.setLugares(4);
            mesa.setLugaresAdicionais(0);
            mesa.setPermiteReserva(false);
            mesa.setNumero(String.valueOf(i));

            mesas.add(mesa);
        }

        return mesaRepository.saveAllAndFlush(mesas);
    }
}
