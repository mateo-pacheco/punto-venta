package com.mateo.springcloud.msvc.oauth.msvc_oauth.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.mateo.springcloud.msvc.oauth.msvc_oauth.Models.Users;

@Component
public class UserService implements UserDetailsService {
    @Autowired
    private WebClient.Builder client;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Ingresando al proceso de login userService::loadUserByUsername con {}", username);
        Map<String, String> params = new HashMap<>();
        params.put("userName", username);
        System.out.println("Params: " + params.toString());
        try {
            Users user = client.build().get().uri("/users/getByUserName/{userName}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Users.class)
                    .block();
            System.out.println(user.toString());
            System.out.println("Roles: " + user.getRoleDomain().toString());
            List<GrantedAuthority> roles = user.getRoleDomain().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
            System.out.println("List<GrantedAuthority>: " + roles.toString());
            logger.info("Se ha iniciado sesion con exito: {}", user);
            logger.info("Password: ", user.getPasswordDomain());
            return new User(user.getUserNameDomain(), user.getPasswordDomain(), user.getEnabledDomain(), true, true, true, roles);
        } catch (WebClientException Eclient) {
            String error = "User not found: " + username;
            logger.error(error);
            throw new UsernameNotFoundException(error);
        }
    }

}
