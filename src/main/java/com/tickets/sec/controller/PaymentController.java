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

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private TokenizationService tokenizationService;


    @Autowired
    private PagoService pagoService;

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
                log.error("Pago rechazado: {}", pagoResponse.getMensaje());
                return ResponseEntity.badRequest().body(pagoResponse);
            }

        }catch (Exception e){
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