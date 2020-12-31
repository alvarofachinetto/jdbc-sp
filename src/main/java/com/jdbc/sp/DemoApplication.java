package com.jdbc.sp;

import com.jdbc.sp.config.Tables;
import com.jdbc.sp.model.Compra;
import com.jdbc.sp.model.Pessoa;
import com.jdbc.sp.model.Produto;
import com.jdbc.sp.respository.CompraRepository;
import com.jdbc.sp.respository.PessoaRepository;
import com.jdbc.sp.respository.PessoaRepositoryImpl;
import com.jdbc.sp.respository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final PessoaRepository pessoaRepository;

	private final ProdutoRepository produtoRepository;

	private final CompraRepository compraRepository;

	private final Tables tables;

	@Override
	public void run(String... args) throws Exception {

//		tables.createTablePessoa();
//		tables.createTableProduto();
//		tables.createTableCompra();
//		tables.createTableCompraProduto();

//		tables.createProcessaItem();

//		Pessoa pessoa = new Pessoa("Alvaro", "12452364471");
//		pessoaRepository.savePessoa(pessoa);
//
//		List<Produto> produtos = List.of(
//				new Produto("Celular Samsung A50", new BigDecimal(1500.00), 7, ""),
//				new Produto("TV OLED LG 40 pol", new BigDecimal(2500.00), 9, ""));
//
//		produtos.forEach(produto -> produtoRepository.saveProduto(produto));

		Compra compra = new Compra(null, "12452364471", "Boleto",
				List.of(new Produto("Celular Samsung A50", new BigDecimal(1500.00), 2, ""),
						new Produto("TV OLED LG 40 pol", new BigDecimal(2500.00), 1, "")));

		compraRepository.realizarCompra(compra);


	}
}
