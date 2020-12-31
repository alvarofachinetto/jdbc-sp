package com.jdbc.sp.respository;

import com.jdbc.sp.model.Pessoa;
import com.jdbc.sp.model.Produto;

import java.util.List;

public interface ProdutoRepository {

    public List<Produto> listarProdutos();

    public Produto saveProduto(Produto produto);
}
