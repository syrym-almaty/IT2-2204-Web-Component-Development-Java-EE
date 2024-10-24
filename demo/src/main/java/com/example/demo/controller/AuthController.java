package com.example.demo.controller;


import com.example.demo.DTO.AuthenticationDTO;
import com.example.demo.DTO.RegistrationDTO;
import com.example.demo.security.JWTUtil;
import com.example.demo.service.RegistrationService;
import com.example.demo.utils.UserValidator;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final RegistrationService registationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        System.out.println(authenticationDTO);
        authenticationDTO.setPassword("{noop}"+authenticationDTO.getPassword());
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getName()
                        ,authenticationDTO.getPassword());
        try {

            authenticationManager.authenticate(authInputToken);
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect credentials");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getName());
        System.out.println(token);
        return Map.of("jwt-token",token);
    }
    @PostMapping("/registration")
    @ResponseBody
    public Map<String,String> performRegistration(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Map.of("message","Error!");
        }
        registationService.registerNewUser(registrationDTO);
        String token = jwtUtil.generateToken(registrationDTO.getName());
        return Map.of("jwt-token",token);
    }
}
