package com.tickets.sec.controller;

import com.tickets.sec.model.Entity.*;
import com.tickets.sec.repository.*;
import com.tickets.sec.utils.Constants;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.dto.Compra;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private AbonadoController abonadoController;
    @Autowired
    private AsientosRepository asientoRepository;
    @Autowired
    private SitioVentaRepository sitioVentaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private VentasAsientosNumeradosRepository ventaAsientosNumeradoRepository;
    @Autowired
    private CredencialesSitioRepository credencialesSitioRepository;

    @PostMapping
    public ResponseEntity<Compra> saveCompra(@RequestBody Compra compra) {

        List<AsientosNumerado> seleccionados = asientoRepository.findSeleccionados(compra.getLocalidad(), compra.getZona(),
                compra.getTipo(), compra.getAsientosSeleccionados());

        VentasAsientosNumerado v = new VentasAsientosNumerado();

        if (seleccionados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            v.setAsientos(
                    seleccionados.stream()
                            .map(AsientosNumerado::getId)
                            .collect(java.util.stream.Collectors.toList()));
            v.setCantidad(seleccionados.size());

            VentasAsientosNumerado ventaNumeradaSaved = ventaAsientosNumeradoRepository.save(v);

            if (compra.getTipoCompra().equals("A")) {
                compra.setComprador(abonadoController.guardarAbonado(compra.getComprador()));
                seleccionados.stream().forEach(a -> {

                    a.setEstado("N");
                    asientoRepository.save(a);

                    Venta venta = new Venta();
                    venta.setVentaNumerada(ventaNumeradaSaved);
                    venta.setPago(new Pago(
                            UUID.randomUUID(),
                            compra.getFormaPago(),
                            java.time.LocalDate.now(),
                            null,
                            "P"));
                    venta.setVendedor(credencialesSitioRepository.findById(compra.getVendedor()).get());
                    venta.setAbonado(compra.getComprador());
                    venta.setFechaVenta(java.time.LocalDate.now());
                    venta.setTotalVenta(java.math.BigDecimal.valueOf
                            (v.getCantidad() * getCostoAsiento(compra.getLocalidad(), compra.getTipo())));

                    ventaRepository.save(venta);
                });
            } else {
                seleccionados.stream().forEach(a -> {
                    a.setEstado("N");
                    asientoRepository.save(a);

                    Venta venta = new Venta();
                    venta.setVentaNumerada(ventaNumeradaSaved);
                    venta.setPago(new Pago(
                            UUID.randomUUID(),
                            compra.getFormaPago(),
                            java.time.LocalDate.now(),
                            null,
                            "P"));
                    venta.setVendedor(credencialesSitioRepository.findById(compra.getVendedor()).get());
                    venta.setFechaVenta(java.time.LocalDate.now());
                    venta.setTotalVenta(java.math.BigDecimal.valueOf
                            (v.getCantidad() * getCostoAsiento(compra.getLocalidad(), compra.getTipo())));

                    ventaRepository.save(venta);
                });
            }
        }

        return ResponseEntity.ok(compra);
    }

    private double getCostoAsiento(String localidad, String tipo) {
        try {
            return Double.parseDouble(String.valueOf(Constants.class.getField("PRECIO_" + localidad +"_" +tipo).get(null)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
