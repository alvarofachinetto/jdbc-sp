package com.jdbc.sp.respository;

import com.jdbc.sp.model.Compra;

public interface CompraRepository {

    public Compra realizarCompra(Compra compra);

    public void cancelarCompra(Long cod);
}
