package com.jdbc.sp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra implements Serializable{

    private Long cod;

    private BigDecimal valorTotal;

    private String cpf;

    private String formaPagamento;

    private List<Produto> itens;

    public Compra(BigDecimal valorTotal, String cpf, String formaPagamento, List<Produto> itens) {
        this.valorTotal = valorTotal;
        this.cpf = cpf;
        this.formaPagamento = formaPagamento;
        this.itens = itens;
    }
}
