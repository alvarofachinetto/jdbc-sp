package com.jdbc.sp.resource;

import com.jdbc.sp.model.Pessoa;
import com.jdbc.sp.respository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaResource {

    private final PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        List<Pessoa> pessoas = pessoaRepository.listarPessoas();
        return ResponseEntity.status(200).body(pessoas);
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa){
        Pessoa novo = pessoaRepository.savePessoa(pessoa);
        if(novo != null)
            return ResponseEntity.status(201).body(novo);
        else
            return ResponseEntity.status(400).build();
    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa){
        Pessoa novo = pessoaRepository.savePessoa(pessoa);
        if(novo != null)
            return ResponseEntity.status(200).body(novo);
        else
            return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/{cod}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long cod){
        pessoaRepository.removePessoa(cod);
        return ResponseEntity.status(204).build();
    }

}
