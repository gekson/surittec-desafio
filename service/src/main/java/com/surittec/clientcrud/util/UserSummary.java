/**
 * 
 */
package com.surittec.clientcrud.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Getter @Setter
public class UserSummary {
	private Long id;
    private String login;
    private String nome;

    public UserSummary(Long id, String login, String nome) {
        this.id = id;
        this.login = login;
        this.nome = nome;
    }
}
