package com.grupo.pag.pagadminapi.controllers;

import com.grupo.pag.pagadminapi.database.entities.Estabelecimento;
import com.grupo.pag.pagadminapi.requests.EstabelecimentoRequest;
import com.grupo.pag.pagadminapi.services.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> findAll() {
        return ResponseEntity.ok(estabelecimentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> findById(@PathVariable Long id) {
        return ResponseEntity.ok(estabelecimentoService.findById(id));
    }
    @GetMapping("logado")
    public ResponseEntity<Estabelecimento> findEstabelecimentoLogado() {
        return ResponseEntity.ok(estabelecimentoService.findByUsuarioLogado());
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> save(@RequestBody EstabelecimentoRequest request) {
        Estabelecimento savedEstabelecimento = estabelecimentoService.save(request);
        return ResponseEntity.ok(savedEstabelecimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> update(@PathVariable Long id, @RequestBody EstabelecimentoRequest request) {
        Estabelecimento updatedEstabelecimento = estabelecimentoService.update(id, request);
        return ResponseEntity.ok(updatedEstabelecimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estabelecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
