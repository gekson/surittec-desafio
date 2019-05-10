/**
 * 
 */
package com.surittec.clientcrud.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surittec.clientcrud.model.Client;
import com.surittec.clientcrud.repository.ClientRepository;
import com.surittec.clientcrud.repository.UserRepository;
import com.surittec.clientcrud.request.ClientRequest;
import com.surittec.clientcrud.response.ClientResponse;
import com.surittec.clientcrud.response.PageResponse;
import com.surittec.clientcrud.security.ApiResponse;
import com.surittec.clientcrud.security.CurrentUser;
import com.surittec.clientcrud.security.PrincipalUser;
import com.surittec.clientcrud.service.ClientService;
import com.surittec.clientcrud.util.Constants;

/**
 * @author gekson
 *
 */
@RestController
@RequestMapping("/api/clientes")
public class ClientController {

	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ClientService clientService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@GetMapping
    public PageResponse<ClientResponse> getAllClient(@CurrentUser PrincipalUser currentUser,
                                                @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        return clientService.getAllClients(currentUser, page, size);
    }
	
	@PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientService.addClient(clientRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{clientId}")
                .buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Cliente Criado com sucesso"));
    }

    @GetMapping("/{clientId}")
    public ClientResponse getClienteById(@CurrentUser PrincipalUser currentUser,
                                    @PathVariable Long clientId) {
        return clientService.getClientById(clientId, currentUser);
    }
}
