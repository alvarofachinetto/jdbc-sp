package com.jdbc.sp.respository;

import com.jdbc.sp.model.Pessoa;

import java.util.List;

public interface PessoaRepository {

    public List<Pessoa> listarPessoas();

    public Pessoa savePessoa(Pessoa pessoa);

    public void removePessoa(Long cod);

}
