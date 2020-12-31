package com.jdbc.sp.respository;

import com.jdbc.sp.model.Pessoa;
import com.jdbc.sp.model.Produto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProdutoRepository")
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Produto> listarProdutos() {
        return jdbcTemplate.query("SELECT * FROM produto",
                (rs, rowNum) ->
                        new Produto(rs.getString("nome"),
                                rs.getBigDecimal("valorUnit"), rs.getInt("qtd"),
                                rs.getString("descricao")));
    }

    @Override
    public Produto saveProduto(Produto produto) {
        int res = jdbcTemplate.update("INSERT INTO produto (nome, valorUnit, qtd, descricao) values(?,?,?,?)",
                produto.getNome(), produto.getValorUnit(), produto.getQtd(), produto.getDescricao());
        if(res > 0)
            return produto;

        return null;
    }
}
