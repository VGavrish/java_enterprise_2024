package com.hillel.app.API;

import dto.SignInRequestDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hillel.app.security.jwt.TokenProvider;
import com.hillel.app.services.UserService;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Data
public class AuthController {
    private final AuthenticationProvider  authenticationProvider;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationProvider  authenticationProvider, TokenProvider tokenProvider, UserService userService) {
        this.authenticationProvider = authenticationProvider;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signin")
    @PreAuthorize("hasRole('USER')")
    public Map<String, Object> signin(@RequestBody SignInRequestDTO requestDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());
        try {
            Authentication authenticate = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenProvider.createToken(authenticate);
            return Map.of("auth_token", token);
        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    //todo move logic to authService

//    @PostMapping("/signup")
//    public ResponseEntity<Long> createUser(@Valid @RequestBody UserDto userDTO) {
//        return new ResponseEntity<>(userService.register(userDTO), HttpStatus.CREATED);
//    }
}
