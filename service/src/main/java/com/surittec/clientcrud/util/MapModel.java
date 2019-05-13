/**
 * 
 */
package com.surittec.clientcrud.util;

import com.surittec.clientcrud.model.Client;
import com.surittec.clientcrud.model.User;
import com.surittec.clientcrud.response.ClientResponse;

/**
 * @author gekson
 *
 */
public class MapModel {
	
	public static ClientResponse mapClientToClientResponse(Client client, User creator) {
        ClientResponse clienteResponse = new ClientResponse();
        clienteResponse.setId(client.getId());
        clienteResponse.setName(client.getNome());
        clienteResponse.setCreatedAt(client.getCreatedAt());       
        clienteResponse.setCpf(client.getCpf());
        clienteResponse.setCep(client.getCep());

        return clienteResponse;
    }
}
