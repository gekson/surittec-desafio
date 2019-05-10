/**
 * 
 */
package com.surittec.clientcrud.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surittec.clientcrud.exception.AppException;
import com.surittec.clientcrud.model.Permit;
import com.surittec.clientcrud.model.PermitEnum;
import com.surittec.clientcrud.model.User;
import com.surittec.clientcrud.repository.PermitRepository;
import com.surittec.clientcrud.repository.UserRepository;
import com.surittec.clientcrud.request.LoginRequest;
import com.surittec.clientcrud.request.SignUpRequest;
import com.surittec.clientcrud.security.ApiResponse;
import com.surittec.clientcrud.security.JwtAuthenticationResponse;
import com.surittec.clientcrud.security.JwtTokenProvider;

/**
 * @author gekson
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermitRepository permitRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
    @SuppressWarnings("unchecked")
	@PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity(new ApiResponse(false, "Usuário já existe!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getLogin(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Permit permit = permitRepository.findByName(PermitEnum.PERMISSAO_COMUM)
                .orElseThrow(() -> new AppException("Permissão não definida."));

        user.setPermits(Collections.singleton(permit));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{login}")
                .buildAndExpand(result.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Usuário cadastrado com sucesso"));
    }

}
