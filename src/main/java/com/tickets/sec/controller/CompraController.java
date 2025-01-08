package com.tickets.sec.controller;

import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.dto.PagoResponse;
import com.tickets.sec.model.Entity.TokenData;
import com.tickets.sec.service.CompraService;
import com.tickets.sec.service.PagoService;
import com.tickets.sec.service.TokenizationService;
import com.tickets.sec.utils.Constants;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<CompraResponse> saveCompra(@RequestBody CompraNumerados compra) {

        CompraResponse compraResponse = compraService.procesarCompra(compra);


        if(compraResponse.getEstado().equals("aprobada")){
            log.info("Compra de asientos numerados completada. Vendedor: {}. #Compra: {}", compra.getVendedor(),compraResponse.getIdCompra() );
            return ResponseEntity.ok(compraResponse);
        }else{
            log.info("La compra de asientos numerados no pudo ser completada. Vendedor: {}", compra.getVendedor());
            return ResponseEntity.badRequest().body(compraResponse);
        }
    }


}
