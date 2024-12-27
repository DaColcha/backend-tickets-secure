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

    public PagoResponse procesarPago(String token, BigDecimal amount) {
        log.info("Processing payment for amount: {}", amount);

        try {
            // Validar y obtener datos del token
            TokenData tokenData = tokenizationService.validateAndGetToken(token);

            // Procesar el pago
            String comprobante = procesamientoEntidad(tokenData, amount);

            // Marcar el token como usado
            tokenData.setUsed(true);

            Pago pago = new Pago();
            pago.setId(UUID.randomUUID());
            pago.setFechaPago(LocalDate.now());
            pago.setFormapago(formaPagoRepository.findById(3).get());
            pago.setComprobante(comprobante);
            pago.setEstado("aprobado");

            pagoRepository.save(pago);

            log.info("Pago procesado con éxito. Comprobante: {}", comprobante);

            return new PagoResponse(
                    pago.getId(),
                    pago.getComprobante(),
                    pago.getEstado(),
                    "Pago procesado con éxito",
                    amount,
                    LocalDateTime.now()
            );

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
        // Aquí iría la integración real con el procesador de pagos
        return "TX-" + System.currentTimeMillis();
    }

}
