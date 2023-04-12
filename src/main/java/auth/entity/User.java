package auth.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "email", length = 200, unique = true)
	private String email;
	
	@NotNull
	@Column(name = "password", length = 200)
	private String password;

	@Column(name = "firstName", length = 20)
	private String firstName;

	@Column(name = "lastName", length = 20)
	private String lastName;
	
	@Column(name = "phone", length = 15)
	private String phone;
	
	@Column(name = "address", length = 255)
	private String address;
	
	@Column(name = "createdDate")
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "active")
	private boolean active;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();


	
}
