package com.jdbc.sp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {

    @JsonIgnore
    private Long cod;

    private String nomeCompl;

    private String cpf;

    public Pessoa(String nomeCompl, String cpf) {
        this.nomeCompl = nomeCompl;
        this.cpf = cpf;
    }
}
