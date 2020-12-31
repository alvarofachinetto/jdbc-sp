package com.jdbc.sp.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Tables {

    private static final Logger logger = LoggerFactory.getLogger(Tables.class);

    private final JdbcTemplate jdbcTemplate;

    public void createTablePessoa(){
        logger.info("Creating table pessoa");
        jdbcTemplate.execute("DROP TABLE IF EXISTS pessoa");
        jdbcTemplate.execute("CREATE TABLE pessoa(" +
                "cod int primary key auto_increment," +
                "nomeCompl VARCHAR(60) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL)");
    }


    public void createTableCompra(){
        logger.info("Creating table Compra");
        jdbcTemplate.execute("DROP TABLE IF EXISTS compra");
        jdbcTemplate.execute("CREATE TABLE compra(" +
                "cod int primary key auto_increment," +
                "valorTotal DECIMAL(10,2) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL," +
                "formaPagamento VARCHAR(10) NOT NULL)");
    }

    public void createTableProduto(){
        logger.info("Creating table Produto");
        jdbcTemplate.execute("DROP TABLE IF EXISTS produto");
        jdbcTemplate.execute("CREATE TABLE produto(" +
                "cod int primary key auto_increment," +
                "nome VARCHAR(60) NOT NULL unique," +
                "valorUnit DECIMAL(10,2) NOT NULL," +
                "qtd int NOT NULL," +
                "descricao varchar(150))");
    }

    public void createTableCompraProduto(){
        logger.info("Creating table CompraProduto");
        jdbcTemplate.execute("DROP TABLE IF EXISTS compraproduto");
        jdbcTemplate.execute("CREATE TABLE compraproduto(" +
                "codCompra int not null," +
                "codProduto int not null," +
                "foreign key (codCompra) references compra(cod)," +
                "foreign key (codProduto) references produto(cod))");
    }

    public void createProcessaItem(){
        logger.info("Creating procedure ProcessaItem");
        jdbcTemplate.execute(
                "create procedure processaItem (nomeProduto varchar(60), qtdProduto int , codCompra int) " +
                "begin " +
                "    declare codProduto int; " +
                "    select p.cod from produto p where p.nome = nomeProduto into codProduto; " +
                "    update produto set qtd = qtd - qtdProduto where cod = codProduto; " +
                "    INSERT INTO compraproduto (codCompra, codProduto) values (codCompra, codProduto); " +
                "end ; ");
    }

}
