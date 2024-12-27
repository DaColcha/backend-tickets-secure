package com.tickets.sec.controller;

import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.dto.PagoResponse;
import com.tickets.sec.service.CompraService;
import com.tickets.sec.service.PagoService;
import com.tickets.sec.utils.Constants;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.sec.dto.CompraNumerados;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<CompraResponse> saveCompra(@RequestBody CompraNumerados compra) {

        CompraResponse compraResponse = new CompraResponse("", "");

        if(compra.getFormaPago().equals(Constants.PAGO_TARJETA)){

            //Calculamos total de la compra
            BigDecimal total = compraService.getTotalVenta(compra.getLocalidad(), compra.getTipo(), compra.getAsientosSeleccionados().size());

            //Procesamos el pago
            PagoResponse pagoResponse = pagoService.procesarPago(compra.getToken(), total);

            //Si el pago fue exitoso, procedemos a guardar la venta
            if (pagoResponse.getEstado().equals("aprobado")){
                compraResponse = compraService.procesarCompra(compra, pagoResponse.getId());
            }else{
                compraResponse = new CompraResponse("rechazada", pagoResponse.getMensaje());
            }
        }else{
            compraResponse = compraService.procesarCompra(compra, null);

        }

        if(compraResponse.getEstado().equals("aprobado")){
            return ResponseEntity.ok(compraResponse);
        }else{
            return ResponseEntity.badRequest().body(compraResponse);
        }
    }


}
