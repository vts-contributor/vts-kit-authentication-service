package auth.config;

import auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class JwtDetails implements UserDetails {

		private static final long serialVersionUID = 1L;

		private Long id;

	    private String firstName;

	    private String email;

	    @JsonIgnore
	    private String password;

		private Collection authorities;

		public JwtDetails(Long id, String firstName, String email, String password,
                          Collection authorities) {
	        this.id = id;
	        this.firstName = firstName;
	        this.email = email;
	        this.password = password;
	        this.authorities = authorities;
	    }

		public static JwtDetails build(User user) {
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName().name()))
					.collect(Collectors.toList());
			JwtDetails jwtDetails = new JwtDetails(
					user.getId(),
					user.getFirstName(),
					user.getEmail(),
					user.getPassword(),
					authorities
			);

	        return jwtDetails;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    @Override
	    public String getUsername() {
	        return email;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
	    public Collection getAuthorities() {
	        return authorities;
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

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        
	        JwtDetails user = (JwtDetails) o;
	        return Objects.equals(id, user.id);
	    }
	}

