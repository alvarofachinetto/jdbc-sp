package com.jdbc.sp.resource;

import com.jdbc.sp.model.Compra;
import com.jdbc.sp.respository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class CompraResource {

    private final CompraRepository compraRepository;

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody Compra compra){
        Compra compraEfetuada = compraRepository.realizarCompra(compra);

        if(compraEfetuada != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(compraEfetuada);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
