/**
 * 
 */
package com.surittec.clientcrud.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Getter @Setter
public class ClientRequest {

	@NotBlank
    @Size(max = 100)
    private String name;
}
