package com.jdbc.sp.respository;

import com.jdbc.sp.config.JDBCConfig;
import com.jdbc.sp.model.Compra;
import com.jdbc.sp.model.Produto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository("CompraRepository")
@RequiredArgsConstructor
public class CompraRepositoryImpl implements CompraRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CompraRepositoryImpl.class);

    private final JDBCConfig jdbcConfig;

    @Override
    public Compra realizarCompra(Compra compra) {
//        Chama a sp
        SimpleJdbcCall simpleJdbcCall =
                new SimpleJdbcCall(jdbcConfig.mysqlDataSource())
                .withProcedureName("processaItem");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Double subTotal = 0.0;

//        calcula o subtotal da compra
        subTotal = compra.getItens()
                    .stream()
                    .mapToDouble(item -> item.getValorUnit().doubleValue() * item.getQtd())
                    .sum();

        compra.setValorTotal(new BigDecimal(subTotal));

//        Faz a inserção da compra e retorna o código da inserção
        int res = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO compra (valorTotal, formaPagamento, cpf) values (?,?,?)", new String[]{"cod"});
                ps.setBigDecimal(1, compra.getValorTotal());
                ps.setString(2, compra.getFormaPagamento());
                ps.setString(3, compra.getCpf());

                return ps;
            }
        }, keyHolder);



        if(res > 0) {
            Long codCompra = keyHolder.getKey().longValue();
            compra.getItens().forEach(
                    item ->{
                        SqlParameterSource params = new MapSqlParameterSource()
                                .addValue("nomeProduto", item.getNome())
                                .addValue("qtdProduto", item.getQtd())
                                .addValue("codCompra", codCompra);
                        simpleJdbcCall.execute(params);
                    }
            );
            return compra;
        }
        return null;
    }

    @Override
    public void cancelarCompra(Long cod) {

    }

}
