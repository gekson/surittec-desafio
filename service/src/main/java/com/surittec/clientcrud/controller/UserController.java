/**
 * 
 */
package com.surittec.clientcrud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surittec.clientcrud.exception.ResourceNotFoundException;
import com.surittec.clientcrud.model.User;
import com.surittec.clientcrud.repository.UserRepository;
import com.surittec.clientcrud.security.CurrentUser;
import com.surittec.clientcrud.security.PrincipalUser;
import com.surittec.clientcrud.util.UserProfile;
import com.surittec.clientcrud.util.UserSummary;

/**
 * @author gekson
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser PrincipalUser currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getLogin(), currentUser.getName());
        return userSummary;
    }
	
	@GetMapping("/users/{login}")
    public UserProfile getUserProfile(@PathVariable(value = "login") String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "login", login));


        UserProfile userProfile = new UserProfile(user.getId(), user.getLogin(), user.getLogin(), user.getCreatedAt());

        return userProfile;
    }
}
