package com.tickets.sec.controller;

import com.tickets.sec.dto.PagoResponse;
import com.tickets.sec.dto.PaymentData;
import com.tickets.sec.service.PagoService;
import com.tickets.sec.service.TokenizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Controlador que maneja las peticiones de pago
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private TokenizationService tokenizationService;


    @Autowired
    private PagoService pagoService;

    /**
     * Metodo que procesa el pago de una compra
     * @see TokenizationService#createToken(PaymentData)
     * @see PagoService#procesarPago(String, BigDecimal, boolean, String)
     *
     * @param cardData Información de la tarjeta de crédito(encriptada) y el monto a procesar
     * @return ResponseEntity<PagoResponse> Respuesta de éxito o de fallo en el proceso de pago
     * con el id de pago que debe proceder a registrar la compra.
     */
    @PostMapping()
    public ResponseEntity<PagoResponse> tokenize(@RequestBody PaymentData cardData) {

        try{
            String token = tokenizationService.createToken(cardData);

            PagoResponse pagoResponse= pagoService.procesarPago(token,
                    cardData.getTotal(), cardData.getUseCard(), cardData.getFormaPago());

            if(pagoResponse.getEstado().equals("aprobado")){
                log.info("Pago {} procesado con exito", pagoResponse.getId());

                return ResponseEntity.ok(pagoResponse);
            }else{
                log.warn("Pago rechazado: {}", pagoResponse.getMensaje());
                return ResponseEntity.badRequest().body(pagoResponse);
            }

        }catch (Exception e){
            log.error("Error al procesar el pago: {}", e.getMessage());
            return ResponseEntity.badRequest().body(
                    new PagoResponse(null,
                            null,
                            "rechazado",
                            e.getMessage(),
                            BigDecimal.valueOf(0.0),
                            LocalDateTime.now())
            );
        }



    }

}
