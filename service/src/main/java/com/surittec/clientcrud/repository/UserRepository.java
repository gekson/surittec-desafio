/**
 * 
 */
package com.surittec.clientcrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surittec.clientcrud.model.User;

/**
 * @author gekson
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIdIn(List<Long> usuarioIds);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

}
