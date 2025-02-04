package com.grupo.pag.pagadminapi.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstabelecimentoRequest {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    private String cnpj;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}\\d{3}|\\d{5}-\\d{3}", message = "O CEP está no formato incorreto.")
    private String cep;

    @NotBlank(message = "A UF é obrigatória.")
    @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve ser uma sigla de 2 letras.")
    private String uf;

    @NotNull(message = "O percentual de serviço é obrigatório.")
    private BigDecimal percentualServico;

    private BigDecimal valorCouvert;

    private Boolean ativarCouvert;

    @NotBlank(message = "O município é obrigatório.")
    private String municipio;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @NotBlank(message = "O nome do responsável é obrigatório.")
    private String nomeResponsavel;

    private String cpfResponsavel;

    @NotBlank(message = "O email é obrigatório.")
    private String email;
    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
    @NotBlank(message = "A chave pix é obrigatória.")
    private String chavePix;
}
