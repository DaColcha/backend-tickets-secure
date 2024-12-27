package com.tickets.sec.controller;

import com.tickets.sec.dto.CompraGeneral;
import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.dto.PagoResponse;
import com.tickets.sec.model.Entity.SitioVenta;
import com.tickets.sec.model.Entity.Venta;
import com.tickets.sec.model.Entity.ZonaGeneral;
import com.tickets.sec.repository.VentaRepository;
import com.tickets.sec.service.AbonadoService;
import com.tickets.sec.service.PagoService;
import com.tickets.sec.service.ZonaGeneralService;
import com.tickets.sec.utils.Constants;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.repository.SitioVentaRepository;
import com.tickets.sec.repository.ZonaGeneralRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/zona-general")
public class ZonaGeneralController {

    @Autowired
    private ZonaGeneralService zonaGeneralService;
    @Autowired
    private PagoService pagoService;

    @GetMapping("/{zona}")
    public Integer getDisponibilidad(@PathVariable String zona) {
        return zonaGeneralService.getDisponibles(zona);
    }

    @PostMapping("/compra")
    public ResponseEntity<CompraResponse> compraGeneralAbonado(@RequestBody CompraGeneral compra) {
        CompraResponse compraResponse = new CompraResponse("", "");
        PagoResponse pagoResponse = new PagoResponse();

        BigDecimal total = zonaGeneralService.calcularTotal(compra.getCantidad());

        if (compra.getFormaPago().equals(Constants.PAGO_TARJETA)) {

            pagoResponse = pagoService.procesarPago(compra.getToken(), total, true, null );

            if (pagoResponse.getEstado().equals("aprobado")) compraResponse = zonaGeneralService.procesarCompraGeneral(compra, pagoResponse.getId());

        }else {
            pagoResponse = pagoService.procesarPago(compra.getToken(), total, false, compra.getFormaPago());

            if (pagoResponse.getEstado().equals("aprobado")) compraResponse = zonaGeneralService.procesarCompraGeneral(compra, null);

        }

        if(!pagoResponse.getEstado().equals("aprobado")) compraResponse = new CompraResponse("rechazada", pagoResponse.getMensaje());

        if(compraResponse.getEstado().equals("aprobada")){
            return ResponseEntity.ok(compraResponse);
        }else{
            return ResponseEntity.badRequest().body(compraResponse);
        }
    }

    @Transactional
    @PatchMapping("/limpiar-todo")
    public ResponseEntity<String> limpiarTodo() {
        zonaGeneralService.limpiarTodo();
        return ResponseEntity.ok("Limpieza exitosa");
    }

    @Transactional
    @PatchMapping("/limpiar")
    public ResponseEntity<String> limpiarGeneral() {
        zonaGeneralService.limpiarGeneral();
        return ResponseEntity.ok("Limpieza exitosa");
    }

}
