/**
 * 
 */
package com.surittec.clientcrud.response;

import java.time.Instant;

import com.surittec.clientcrud.util.UserSummary;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Getter
@Setter
public class ClientResponse {
	private Long id;
	private String name;
	private UserSummary createdBy;
    private Instant createdAt;
    private String cpf;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String complemento;
}
