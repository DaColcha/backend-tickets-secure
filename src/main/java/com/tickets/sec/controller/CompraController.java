package com.tickets.sec.controller;

import com.tickets.sec.repository.AsientosRepository;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.model.Compra;
import com.tickets.sec.repository.SitioVentaRepository;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Compra> saveCompra(@RequestBody Compra compra) {

        SitioVenta sitioVenta = sitioVentaRepository.findByNombreSitio(compra.getSitioVenta());

        List<Asiento> seleccionados = asientoRepository.findSeleccionados(compra.getLocalidad(), compra.getZona(),
                compra.getTipo(), compra.getAsientosSeleccionados());

        if (seleccionados.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            if (compra.getTipoCompra().equals("A")) {
                compra.setComprador(compradorController.guardarComprador(compra.getComprador()));
                seleccionados.stream().forEach(a -> {
                    a.setEstado("N");
                    a.setComprador(compra.getComprador());
                    a.setTipoCompra(compra.getTipoCompra());
                    a.setSitioVenta(sitioVenta);
                    a.setPago(compra.getPago());
                    a.setPlazo(compra.getPlazo());
                    asientoRepository.save(a);
                });
            } else {
                seleccionados.stream().forEach(a -> {
                    a.setEstado("N");
                    a.setTipoCompra(compra.getTipoCompra());
                    a.setSitioVenta(sitioVenta);
                    asientoRepository.save(a);
                });
            }
        }

        return ResponseEntity.ok(compra);
    }

}
