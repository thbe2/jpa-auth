package fr.ajc.securityExoFinal.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class CustomUser {
// On ne l'appelle pas "User" car c'est déjà utilisé par Spring security

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role"))
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CustomRole> roles;

	public CustomUser(String username, String password, List<CustomRole> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public CustomUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	} 
}
