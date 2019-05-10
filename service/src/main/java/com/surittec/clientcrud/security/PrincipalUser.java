/**
 * 
 */
package com.surittec.clientcrud.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.surittec.clientcrud.model.User;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gekson
 *
 */
@Getter @Setter
public class PrincipalUser implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String login;
    
    private String name;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> permits;

    public PrincipalUser(Long id, String login, String password, Collection<? extends GrantedAuthority> permits) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.permits = permits;
    }

    public static PrincipalUser create(User user) {
        List<GrantedAuthority> permits = user.getPermits().stream().map(permit ->
                new SimpleGrantedAuthority(permit.getName().name())
        ).collect(Collectors.toList());

        return new PrincipalUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                permits
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrincipalUser that = (PrincipalUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permits;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
