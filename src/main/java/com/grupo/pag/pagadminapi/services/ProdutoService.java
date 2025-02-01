package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.database.entities.Categoria;
import com.grupo.pag.pagadminapi.database.entities.Estabelecimento;
import com.grupo.pag.pagadminapi.database.entities.Produto;
import com.grupo.pag.pagadminapi.database.repository.CategoriaRepository;
import com.grupo.pag.pagadminapi.database.repository.EstabelecimentoRepository;
import com.grupo.pag.pagadminapi.database.repository.ProdutoRepository;
import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public List<Produto> criarProdutosParaEstabelecimento(Long estabelecimentoId) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Estabelecimento não encontrado"));

        List<Categoria> categorias = categoriaRepository.findByEstabelecimentoId(estabelecimentoId);
        
        if (categorias.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhuma categoria encontrada para o estabelecimento");
        }

        List<Produto> produtos = new ArrayList<>();

        Categoria petiscosCategoria = categorias.stream()
            .filter(c -> c.getDescricao().equalsIgnoreCase("Petiscos"))
            .findFirst()
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria Petiscos não encontrada"));

        produtos.add(criarProduto("Batata Frita", "Porção de batata frita crocante", petiscosCategoria, estabelecimento, new BigDecimal("25.90")));
        produtos.add(criarProduto("Frango à Passarinho", "Porção de frango frito", petiscosCategoria, estabelecimento, new BigDecimal("35.90")));
        produtos.add(criarProduto("Calabresa Acebolada", "Calabresa grelhada com cebola", petiscosCategoria, estabelecimento, new BigDecimal("29.90")));

        Categoria bebidasCategoria = categorias.stream()
            .filter(c -> c.getDescricao().equalsIgnoreCase("Bebidas"))
            .findFirst()
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria Bebidas não encontrada"));

        produtos.add(criarProduto("Coca-Cola", "Refrigerante Coca-Cola 350ml", bebidasCategoria, estabelecimento, new BigDecimal("7.90")));
        produtos.add(criarProduto("Água Mineral", "Água sem gás 500ml", bebidasCategoria, estabelecimento, new BigDecimal("4.50")));
        produtos.add(criarProduto("Suco Natural", "Suco de laranja natural", bebidasCategoria, estabelecimento, new BigDecimal("9.90")));

        Categoria drinksCategoria = categorias.stream()
            .filter(c -> c.getDescricao().equalsIgnoreCase("Drinks"))
            .findFirst()
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria Drinks não encontrada"));

        produtos.add(criarProduto("Caipirinha", "Caipirinha tradicional de limão", drinksCategoria, estabelecimento, new BigDecimal("18.90")));
        produtos.add(criarProduto("Mojito", "Drink refrescante de hortelã", drinksCategoria, estabelecimento, new BigDecimal("22.90")));
        produtos.add(criarProduto("Margarita", "Drink clássico de tequila", drinksCategoria, estabelecimento, new BigDecimal("24.90")));

        // Default produtos for Refeições category
        Categoria refeicoesCategoria = categorias.stream()
            .filter(c -> c.getDescricao().equalsIgnoreCase("Refeições"))
            .findFirst()
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria Refeições não encontrada"));

        produtos.add(criarProduto("Filé Mignon", "Filé mignon grelhado com batatas", refeicoesCategoria, estabelecimento, new BigDecimal("59.90")));
        produtos.add(criarProduto("Risoto de Camarão", "Risoto cremoso com camarões", refeicoesCategoria, estabelecimento, new BigDecimal("65.90")));
        produtos.add(criarProduto("Lasanha à Bolonhesa", "Lasanha tradicional", refeicoesCategoria, estabelecimento, new BigDecimal("42.90")));

        return produtoRepository.saveAllAndFlush(
                produtos);
    }

    private Produto criarProduto(String nome, String descricao, Categoria categoria, Estabelecimento estabelecimento, BigDecimal preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setEstabelecimento(estabelecimento);
        produto.setPreco(preco);
        produto.setCodigo(gerarCodigoProduto(categoria.getDescricao()));
        return produto;
    }

    private String gerarCodigoProduto(String categoriaNome) {
        String prefixo = categoriaNome.substring(0, 3).toUpperCase();
        String sufixo = String.valueOf(System.currentTimeMillis() % 10000);
        return prefixo + sufixo;
    }
}
