package com.tickets.sec.controller;

import com.tickets.sec.dto.EncryptedData;
import com.tickets.sec.dto.Tarjeta;
import com.tickets.sec.service.TokenizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tokenize")
public class TokenizeController {

    @Autowired
    private TokenizationService tokenizationService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> tokenize(@RequestBody EncryptedData cardData) {

        try{
            String token = tokenizationService.createToken(cardData.getEncryptedData());
            return ResponseEntity.ok(Map.of("token", token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

    }

}
