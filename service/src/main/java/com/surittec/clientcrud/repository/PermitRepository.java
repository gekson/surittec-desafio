/**
 * 
 */
package com.surittec.clientcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surittec.clientcrud.model.Permit;
import com.surittec.clientcrud.model.PermitEnum;

/**
 * @author gekson
 *
 */
@Repository
public interface PermitRepository extends JpaRepository<Permit, Long> {
    Optional<Permit> findByName(PermitEnum name);

}
