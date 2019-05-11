/**
 * 
 */
package com.surittec.clientcrud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surittec.clientcrud.config.SecurityConfig;
import com.surittec.clientcrud.repository.PermitRepository;
import com.surittec.clientcrud.repository.UserRepository;
import com.surittec.clientcrud.request.SignUpRequest;
import com.surittec.clientcrud.security.CustomUserDetailsService;
import com.surittec.clientcrud.security.JwtTokenProvider;

/**
 * @author Gekson.Silva
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
	
	@MockBean
    AuthenticationManager authenticationManager;

	@MockBean
    UserRepository userRepository;

	@MockBean
    PermitRepository permitRepository;

	@MockBean
    PasswordEncoder passwordEncoder;

	@MockBean
    JwtTokenProvider tokenProvider;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CustomUserDetailsService customUserDetailsService;
	
	@MockBean
	SecurityConfig securityConfig;
	
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws Exception {
		SignUpRequest request = new SignUpRequest();
		request.setLogin("testUser");
		request.setPassword("123");
//		
//		User user = new User(request.getLogin(), request.getPassword());
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        
//        when(userRepository.save(any(SignUpRequest.class))).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/signin")
				.content(asJsonString(request))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("").exists());
               
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
