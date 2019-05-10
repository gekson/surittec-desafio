/**
 * 
 */
package com.surittec.clientcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.surittec.clientcrud.audit.UserAndDateAudit;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Entity
@Table(name = "clients")
@Getter @Setter
public class Client extends UserAndDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(max = 100, min = 3)
	@Column
    private String nome;
	
	@NotBlank
	@Column
	private String cpf;
	
	@NotBlank
	@Column
	private String cep;
	
	@NotBlank
	@Column
	private String logradouro;
	
	@NotBlank
	@Column
	private String bairro;
	
	@NotBlank
	@Column
	private String cidade;
	
	@NotBlank
	@Column
	private String uf;
	
	@Column
	private String complemento;
}
