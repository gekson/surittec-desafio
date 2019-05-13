/**
 * 
 */
package com.surittec.clientcrud.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Getter @Setter
public class ClientRequest {
	
    private String name;	
    private String cpf;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private String complemento;
}
