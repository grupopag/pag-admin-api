package com.grupo.pag.pagadminapi.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstabelecimentoRequest {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
    private String cnpj;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000.")
    private String cep;

    @NotBlank(message = "A UF é obrigatória.")
    @Size(min = 2, max = 2, message = "A UF deve conter 2 caracteres.")
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

    private String email;

    private String senha;

}
