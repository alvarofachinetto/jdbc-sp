package com.jdbc.sp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento implements Serializable{

    private Long cod;

    private String formaPagamento;

    private BigDecimal valorDivida;

    private BigDecimal valorPago;

    private LocalDate dataPagamento;

    private Boolean pagoAposVencimento;

    private List<Compra> comprasFeitas = new ArrayList<>();
}
