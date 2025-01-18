package com.tickets.sec.service;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CredencialesRepository credencialesRepository;

    /**
     * Método que carga los datos de un usuario en base a su nombre de usuario.
     * 
     * @see com.tickets.sec.repository.CredencialesRepository
     * 
     * @param usuario Nombre de usuario.
     * @return org.springframework.security.core.userdetails.UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Credenciales credenciales = credencialesRepository.findFirstByUsuario(usuario);
        if (credenciales == null) {
            throw new UsernameNotFoundException(usuario);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    credenciales.getUsuario(),
                    credenciales.getContrasena(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + credenciales.getRol()))
            );
        }
    }
}
