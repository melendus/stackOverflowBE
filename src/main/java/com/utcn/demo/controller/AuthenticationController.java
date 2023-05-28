package com.utcn.demo.controller;

import com.utcn.demo.dtos.AuthenticationRequest;
import com.utcn.demo.dtos.LoginResponseDto;
import com.utcn.demo.dtos.UserDto;
import com.utcn.demo.entity.User;
import com.utcn.demo.security.config.JwtUtils;
import com.utcn.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    private final UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<LoginResponseDto> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        System.out.println("email---->" +  request.getEmail());


        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (userDetails != null) {
            Optional<User> user = userService.getUserByEmail(request.getEmail());

            LoginResponseDto loginResponseDto = new LoginResponseDto(jwtUtils.generateToken(userDetails), user);

            return ResponseEntity.ok().body(loginResponseDto);
        }

        return ResponseEntity.status(400).body(new LoginResponseDto("ERROR", null));
    }

    @GetMapping("/some-endpoint")
    public ResponseEntity<?> someEndpoint(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println(role);

        return null;
    }
}

