package me.latanks.ecommerce_api.services.impl;

import me.latanks.ecommerce_api.exception.impl.InvalidCredentialsException;
import me.latanks.ecommerce_api.exception.impl.UserNotFoundedException;
import me.latanks.ecommerce_api.models.User;
import me.latanks.ecommerce_api.models.dto.request.LoginDTO;
import me.latanks.ecommerce_api.models.dto.response.LoggedDTO;
import me.latanks.ecommerce_api.models.enums.Roles;
import me.latanks.ecommerce_api.repositories.IUserRepository;
import me.latanks.ecommerce_api.security.JWTUtil;
import me.latanks.ecommerce_api.security.UserDetailsServiceImpl;
import me.latanks.ecommerce_api.security.UserSpringSecurity;
import me.latanks.ecommerce_api.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserRepository userService;
    @Autowired
    private UserDetailsServiceImpl  userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoggedDTO login(LoginDTO data) {
        User user = this.userService.findByEmail(data.email()).orElseThrow(
                () -> new UserNotFoundedException("User with username " + data.email() + " not found")
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        if(!passwordEncoder.matches(data.password(), userDetails.getPassword())) {
            throw new InvalidCredentialsException("Senha incorreta");
        }


        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getAuthorities().stream().findFirst().get());

        String token = jwtUtil.generateToken(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), data.password()));

        return new LoggedDTO(
                "Bearer " + token,
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(Roles::getDescription).collect(Collectors.joining(", \n"))
        );
    }
}
