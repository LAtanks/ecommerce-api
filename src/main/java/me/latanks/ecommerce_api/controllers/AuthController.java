package me.latanks.ecommerce_api.controllers;

import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.LoginDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;
import me.latanks.ecommerce_api.models.dto.response.LoggedDTO;
import me.latanks.ecommerce_api.services.impl.AuthServiceImpl;
import me.latanks.ecommerce_api.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthServiceImpl  authService;

    @PostMapping("/login")
    public ResponseEntity<LoggedDTO> login(@RequestBody LoginDTO data) {
        return ResponseEntity.ok(authService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<CreatedUserDTO>  register(@RequestBody CreateUserDTO data) {
        return ResponseEntity.ok(this.userService.create(data));
    }
}
