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


    @PostMapping
    public ResponseEntity<Estabelecimento> save(@RequestBody EstabelecimentoRequest request) {
        Estabelecimento savedEstabelecimento = estabelecimentoService.save(request);
        return ResponseEntity.ok(savedEstabelecimento);
    }



}
