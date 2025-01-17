package com.tickets.sec.filter;

import com.tickets.sec.model.Entity.Credenciales;
import com.tickets.sec.repository.CredencialesRepository;
import com.tickets.sec.service.JwtService;
import com.tickets.sec.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFiltroAutenticacion extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CredencialesRepository credencialesRepository;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Método que filtra las peticiones HTTP y verifica la validez de los tokens JWT.
     * @see org.springframework.web.filter.OncePerRequestFilter
     * @see com.tickets.sec.service.JwtService
     * @see com.tickets.sec.repository.CredencialesRepository
     * @see com.tickets.sec.service.UserDetailsServiceImpl
     * 
     * @param request Petición HTTP.
     * @param response Respuesta HTTP.
     * @param filterChain Cadena de filtros.
     * 
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth/login")
                || request.getServletPath().contains("/auth/verify-otp")
                || request.getServletPath().contains("/swagger-ui")
                || request.getServletPath().contains("/v3/api-docs")
                || request.getServletPath().contains("/swagger-resources")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido o expirado");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.substring(7);
        final String usuario = jwtService.extraerUsuario(jwtToken);
        if (usuario == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido o expirado");
            return;
        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(usuario);
        final Credenciales credenciales = credencialesRepository.findFirstByUsuario(userDetails.getUsername());
        if (credenciales == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final boolean tokenValido = jwtService.verificarValidezToken(jwtToken, credenciales);
        if (!tokenValido) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido o expirado");
            return;
        }

        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
