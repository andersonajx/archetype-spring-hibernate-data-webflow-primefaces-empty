package br.com.example.auth;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public final class User implements UserDetails, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Integer version;
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String login;
	@NotNull
	@NotEmpty
	private String password;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role = Role.ROLE_USER;

	private User() {
	}

	private User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public static User newInstance() {
		return new User();
	}

	public static User newInstance(String login, String password) {
		checkNotNull(login, "Login must not be null");
		checkArgument(!login.isEmpty(), "Login must not be empty");
		checkNotNull(password, "Password must not be null");
		checkArgument(!password.isEmpty(), "Password must not be empty");
		return new User(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			return Objects.equal(login, other.login)
					&& Objects.equal(password, other.password);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(login, password);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(login).toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = Lists.newLinkedList();
		roles.add(role);
		return Collections.unmodifiableList(roles);
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

	Long getId() {
		return id;
	}

	void setLogin(String login) {
		this.login = login;
	}

	void setPassword(String password) {
		this.password = password;
	}

	void setRole(Role role) {
		this.role = role;
	}
}
