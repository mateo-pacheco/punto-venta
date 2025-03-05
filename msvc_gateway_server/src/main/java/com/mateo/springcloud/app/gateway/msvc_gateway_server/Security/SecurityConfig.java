package com.mateo.springcloud.app.gateway.msvc_gateway_server.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

import org.springframework.core.convert.converter.Converter;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {
    private final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
        logger.info("Ingresando al fiterChain");
        return httpSecurity.authorizeExchange(authenticated -> {
            authenticated.pathMatchers("/authorized", "/logout").permitAll()
                    .pathMatchers("/api/admin/**").hasRole("ADMIN")
                    .pathMatchers("/api/usuario/**").hasAnyRole("ADMIN", "USER")
                    .pathMatchers("/api/inventario/**").hasRole("ADMIN")
                    .pathMatchers("/api/registro-ventas/**").hasRole("ADMIN")
                    .pathMatchers("/api/reporte-ventas/**").hasRole("ADMIN")
                    .pathMatchers("/api/punto-venta/**").hasAnyRole("ADMIN", "USER")
                    .anyExchange().authenticated();
        }).cors(csrf -> csrf.disable())
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(new Converter<Jwt, Mono<AbstractAuthenticationToken>>() {

                            @SuppressWarnings("null")
                            @Override
                            @Nullable
                            public Mono<AbstractAuthenticationToken> convert(Jwt source) {
                                Collection<String> roles = source.getClaimAsStringList("roles");
                                Collection<GrantedAuthority> authorities = roles.stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList());
                                return Mono.just(new JwtAuthenticationToken(source, authorities));
                            }

                        })))
                .build();
    }
}
