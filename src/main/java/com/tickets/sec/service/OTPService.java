package com.tickets.sec.service;

import com.tickets.sec.model.Entity.OTP;
import com.tickets.sec.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPService {
    @Autowired
    private OtpRepository otpRepository;

    // Generar un OTP y almacenarlo en la base de datos
    public String generateOTP(String username) {
        // Generar un código OTP de 6 dígitos
        String otp = String.format("%06d", new SecureRandom().nextInt(1000000));

        // Crear una nueva entidad OTP
        OTP otpEntity = new OTP();
        otpEntity.setUsername(username);
        otpEntity.setOtp(otp);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(5)); // Expira en 5 minutos

        // Guardar en la base de datos
        otpRepository.save(otpEntity);

        return otp;
    }

    // Validar el OTP
    public boolean validateOTP(String username, String otp) {
        Optional<OTP> otpEntity = otpRepository.findByUsername(username);
        if (otpEntity.isPresent() && otpEntity.get().getOtp().equals(otp)) {

            if (otpEntity.get().getExpirationTime().isAfter(LocalDateTime.now())) {
                otpRepository.deleteByUsername(username); // Eliminar el OTP usado
                return true;
            }
        }
        return false;
    }
}

