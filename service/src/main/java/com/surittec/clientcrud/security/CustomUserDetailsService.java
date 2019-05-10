/**
 * 
 */
package com.surittec.clientcrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surittec.clientcrud.model.User;
import com.surittec.clientcrud.repository.UserRepository;

/**
 * @author gekson
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("Usuaário não existe com este login : " + login)
        );

        return PrincipalUser.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
    	User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("Usuaário não encontrado com o id : " + id)
        );

        return PrincipalUser.create(user);
    }

}
