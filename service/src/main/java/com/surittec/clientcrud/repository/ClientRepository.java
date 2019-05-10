/**
 * 
 */
package com.surittec.clientcrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surittec.clientcrud.model.Client;

/**
 * @author gekson
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long clienteId);

    Page<Client> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    List<Client> findByIdIn(List<Long> clienteIds);

    List<Client> findByIdIn(List<Long> clienteIds, Sort sort);

}
