package com.jdbc.sp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

    @Id
    private String cod;

    private String nome;

    private BigDecimal valorUnit;

    private Integer qtd;

    private String descricao;

    public Produto(String nome, BigDecimal valorUnit, Integer qtd, String descricao) {
        this.nome = nome;
        this.valorUnit = valorUnit;
        this.qtd = qtd;
        this.descricao = descricao;
    }
}
