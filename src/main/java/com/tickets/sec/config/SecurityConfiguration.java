package com.tickets.sec.config;

import com.tickets.sec.filter.JwtFiltroAutenticacion;
import com.tickets.sec.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFiltroAutenticacion jwtFiltroAutenticacion;
    @Value("${origin.url}")
    private String originUrl;

    /**
     * Configuración del CORS.
     *
     * Se especifica el origen permitido para las peticiones usando una variable de entorno.
     * Se permiten los métodos GET, POST, PUT y PATCH.
     * Se permite el envío de cookies y de cabeceras.
     *
     */
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(originUrl)
                .allowedMethods("GET", "POST", "PUT", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Configuración de la autenticación de los usuarios.
     * 
     * Se especifica la clase encargada de manejar datos de los usuarios.
     * @see com.tickets.sec.service.UserDetailsServiceImpl
     * 
     * Se especifica el cifrado de las contraseñas.
     * 
     * @return org.springframework.security.authentication.AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Determina el cifrado de las contraseñas que será usado para la autenticación de usuarios.
     * 
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración de la seguridad general de las rutas de la aplicación.
     * 
     * Se especifica que las rutas de Swagger y de autenticación no requieren autenticación.
     * 
     * Se especifica que la sesión es sin estado.
     * 
     * Se especifica el proveedor de autenticación.
     * @see SecurityConfiguration#authenticationProvider()
     * 
     * Se especifica el filtro de autenticación JWT para cada petición.
     * @see com.tickets.sec.filter.JwtFiltroAutenticacion
     * 
     * @param http HttpSecurity
     * @return org.springframework.security.web.SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/auth/login",
                                "/auth/verify-otp"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFiltroAutenticacion, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
