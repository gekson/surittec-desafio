/**
 * 
 */
package com.surittec.clientcrud.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.surittec.clientcrud.exception.BadRequestException;
import com.surittec.clientcrud.exception.ResourceNotFoundException;
import com.surittec.clientcrud.model.Client;
import com.surittec.clientcrud.model.User;
import com.surittec.clientcrud.repository.ClientRepository;
import com.surittec.clientcrud.repository.UserRepository;
import com.surittec.clientcrud.request.ClientRequest;
import com.surittec.clientcrud.response.ClientResponse;
import com.surittec.clientcrud.response.PageResponse;
import com.surittec.clientcrud.security.PrincipalUser;
import com.surittec.clientcrud.util.Constants;
import com.surittec.clientcrud.util.MapModel;

/**
 * @author gekson
 *
 */
@Service
public class ClientService {
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

	public PageResponse<ClientResponse> getAllClients(PrincipalUser currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Client> clients = clientRepository.findAll(pageable);

        if(clients.getNumberOfElements() == 0) {
            return new PageResponse<>(Collections.emptyList(), clients.getNumber(),
                    clients.getSize(), clients.getTotalElements(), clients.getTotalPages(), clients.isLast());
        }

        List<Long> clientIds = clients.map(Client::getId).getContent();
        Map<Long, User> creatorMap = getClientCreatorMap(clients.getContent());
        		
        List<ClientResponse> clienteResponses = clients.map(client -> {
            return MapModel.mapClientToClientResponse(client, creatorMap.get(client.getCreatedBy()));
        }).getContent();

        return new PageResponse<>(clienteResponses, clients.getNumber(),
                clients.getSize(), clients.getTotalElements(), clients.getTotalPages(), clients.isLast());
	}
	
	Map<Long, User> getClientCreatorMap(List<Client> clients) {
        List<Long> creatorIds = clients.stream()
                .map(Client::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }

	public Client addClient(@Valid ClientRequest clienteRequest) {
		Client client = new Client();
        client.setNome(clienteRequest.getName());
        client.setCpf(clienteRequest.getCpf());
        client.setCep(clienteRequest.getCep());
        client.setLogradouro(clienteRequest.getLogradouro());
        client.setBairro(clienteRequest.getBairro());
        client.setCidade(clienteRequest.getCidade());
        client.setUf(clienteRequest.getUf());
        client.setComplemento(clienteRequest.getComplemento());

        return clientRepository.save(client);
	}

	public ClientResponse getClientById(Long clientId, PrincipalUser currentUser) {
		Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", clientId));
		
        User creator = userRepository.findById(client.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", client.getCreatedBy()));

		return MapModel.mapClientToClientResponse(client, creator);
	}
	
	private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("O número da página não pode ser menor que zero.");
        }

        if(size > Constants.MAX_PAGE_SIZE) {
            throw new BadRequestException("O tamanho da página não deve ser maior quen " + Constants.MAX_PAGE_SIZE);
        }
    }
}
