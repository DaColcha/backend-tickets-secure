package com.tickets.sec.model.Reports;

import java.math.BigDecimal;

public class AbonadosTotales {

    private String tipoCompra;
    private BigDecimal totalVendidos;

    public AbonadosTotales(String tipoCompra, BigDecimal totalVendidos) {
        this.tipoCompra = tipoCompra;
        this.totalVendidos = totalVendidos;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public BigDecimal getTotalVendidos() {
        return totalVendidos;
    }

    public void setTotalVendidos(BigDecimal totalVendidos) {
        this.totalVendidos = totalVendidos;
    }

}
