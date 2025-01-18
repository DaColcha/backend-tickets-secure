package com.tickets.sec.controller;

import com.tickets.sec.dto.CompraResponse;
import com.tickets.sec.service.CompraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.tickets.sec.dto.CompraNumerados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Clase que controla las peticiones de compra de asientos numerados
 */
@RestController
@RequestMapping("/compra")
@Slf4j
public class CompraController {

    @Autowired
    private CompraService compraService;

    /**
     * Metodo que procesa la compra de asientos numerados
     * @see CompraService#procesarCompra(CompraNumerados)
     * @see CompraNumerados
     *
     * @param compra Información de la compra a registrarse
     * @return ResponseEntity<CompraResponse> Respuesta de éxito o de fallo en el proceso de compra.
     */
    @PostMapping
    public ResponseEntity<CompraResponse> saveCompra(@RequestBody CompraNumerados compra) {

        CompraResponse compraResponse = compraService.procesarCompra(compra);


        if(compraResponse.getEstado().equals("aprobada")){
            log.info("Compra de asientos numerados completada. Vendedor: {}. #Compra: {}", compra.getVendedor(),compraResponse.getIdCompra() );
            return ResponseEntity.ok(compraResponse);
        }else{
            log.warn("La compra de asientos numerados no pudo ser completada. Vendedor: {}. Localidad: {}. {}",
                    compra.getVendedor(),
                    compra.getLocalidad() + "-" + compra.getZona() + "-" + compra.getTipo(),
                    compraResponse.getMensaje());
            return ResponseEntity.badRequest().body(compraResponse);
        }
    }


}
