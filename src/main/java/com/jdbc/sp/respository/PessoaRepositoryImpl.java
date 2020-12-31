package com.jdbc.sp.respository;

import com.jdbc.sp.model.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PessoaRepository")
@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Pessoa> listarPessoas() {
        return jdbcTemplate.query("SELECT * FROM pessoa",
                (rs, rowNum) ->
                    new Pessoa(rs.getString("nomeCompl"), rs.getString("cpf")));
    }

    @Override
    public Pessoa savePessoa(Pessoa pessoa) {
        List<String> cpf = jdbcTemplate.queryForList("SELECT cpf FROM pessoa where cpf = "+ pessoa.getCpf(), String.class);
        int res = 0;

        if(!cpf.isEmpty()){
            res = jdbcTemplate.update("UPDATE pessoa set nomeCompl = ? WHERE cpf = ?", pessoa.getNomeCompl(), pessoa.getCpf());
            if(res > 0)
                return pessoa;
        }else {
            res = jdbcTemplate.update("INSERT INTO pessoa (nomeCompl, cpf) values(?,?)", pessoa.getNomeCompl(), pessoa.getCpf());
            if(res > 0)
                return pessoa;
        }
        return null;
    }

    @Override
    public void removePessoa(Long cod) {
        jdbcTemplate.update("DELETE pessoa WHERE cod = ? ", cod);
    }

}
