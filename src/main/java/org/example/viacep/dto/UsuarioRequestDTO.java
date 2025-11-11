package org.example.viacep.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    private String nome;
    private String cel;
    private String cep;

}