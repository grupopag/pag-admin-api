package com.grupo.pag.pagadminapi.controllers;

import com.grupo.pag.pagadminapi.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("{token}")
    public ResponseEntity<?> getToken(@PathVariable String token) {
        return ResponseEntity.ok(tokenService.findTokenByToken(token));
    }

}
