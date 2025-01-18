package com.tickets.sec.service;

import com.tickets.sec.dto.PagoResponse;
import com.tickets.sec.model.Entity.Pago;
import com.tickets.sec.model.Entity.TokenData;
import com.tickets.sec.repository.FormaPagoRepository;
import com.tickets.sec.repository.PagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PagoService {
    @Autowired
    private TokenizationService tokenizationService;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;


    public PagoResponse procesarPago(String token, BigDecimal amount, boolean useCard, String formaPago) {
        log.info("Procesando pago de: {}", amount);

        try {
            TokenData tokenData = tokenizationService.validateAndGetToken(token);

            String comprobante = procesamientoEntidad(tokenData, amount);

            tokenData.setUsed(true);

            Pago pago = new Pago();
            pago.setId(UUID.randomUUID());
            pago.setFechaPago(LocalDate.now());
            pago.setEstado("aprobado");

            if(useCard){
                pago.setFormapago(formaPagoRepository.findById(3).get());
                pago.setComprobante(comprobante);

                pagoRepository.save(pago);

                log.info("Pago con tarjeta procesado con exito. Comprobante: {}", comprobante);

                return new PagoResponse(
                        pago.getId(),
                        pago.getComprobante(),
                        pago.getEstado(),
                        "Pago con tarjeta procesado con éxito",
                        amount,
                        LocalDateTime.now()
                );
            }else{
                pago.setFormapago(formaPagoRepository.findByFormaPago(formaPago));
                pagoRepository.save(pago);

                log.info("Pago sin  tarjeta procesado con éxito.");
                return new PagoResponse(
                        pago.getId(),
                        null,
                        "aprobado",
                        "Pago sin tarjeta procesado con éxito",
                        amount,
                        LocalDateTime.now()
                );
            }

        } catch (Exception e) {
            log.error("Error al procesar pago: {}", e.getMessage());
            return new PagoResponse(
                    null,
                    null,
                    "rechazado",
                    e.getMessage(),
                    amount,
                    LocalDateTime.now()
            );
        }
    }

    private String procesamientoEntidad(TokenData tokenData, BigDecimal amount) {
        // Simulación de procesamiento
        // Aquí se implementaría la integración real con el procesador de pagoss
        return "TX-" + System.currentTimeMillis();
    }

}
