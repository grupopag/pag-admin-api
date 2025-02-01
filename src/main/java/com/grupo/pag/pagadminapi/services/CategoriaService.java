package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.database.entities.Categoria;
import com.grupo.pag.pagadminapi.database.entities.Estabelecimento;
import com.grupo.pag.pagadminapi.database.repository.CategoriaRepository;
import com.grupo.pag.pagadminapi.database.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    @Transactional
    public List<Categoria> criarCategoriasParaEstabelecimento(Long estabelecimentoId) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
            .orElseThrow(() -> new IllegalArgumentException("Estabelecimento não encontrado com ID: " + estabelecimentoId));

        String[] descricoesCategorias = {
            "Petiscos", 
            "Bebidas", 
            "Drinks", 
            "Refeições"
        };

        List<Categoria> categorias = new ArrayList<>();

        for (int i = 0; i < descricoesCategorias.length; i++) {
            Categoria categoria = new Categoria();
            categoria.setDescricao(descricoesCategorias[i]);
            categoria.setEstabelecimentoId(estabelecimento.getId());
            categoria.setOrdem(i + 1);

            categorias.add(categoria);
        }

        return categoriaRepository.saveAllAndFlush(categorias);
    }
}
