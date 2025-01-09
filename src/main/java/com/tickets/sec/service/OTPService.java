package com.tickets.sec.service;

import com.tickets.sec.model.Entity.OTP;
import com.tickets.sec.repository.OtpRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class OTPService {
    @Autowired
    private OtpRepository otpRepository;

    public String generateOTP(String username) {
        String otp = String.format("%06d", new SecureRandom().nextInt(1000000));

        OTP otpEntity = new OTP();
        otpEntity.setUsername(username);
        otpEntity.setOtp(otp);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpEntity);

        log.info("Se ha generado un nuevo código OTP");
        return otp;
    }

    // Validar el OTP
    @Transactional
    public boolean validateOTP(String username, String otp) {
        Optional<OTP> otpEntity = otpRepository.findTopByUsernameOrderByIdDesc(username);
        if (otpEntity.isPresent() && otpEntity.get().getOtp().equals(otp)) {

            if (otpEntity.get().getExpirationTime().isAfter(LocalDateTime.now())) {
                otpRepository.deleteByUsername(username);
                return true;
            }
        }
        return false;
    }
}

