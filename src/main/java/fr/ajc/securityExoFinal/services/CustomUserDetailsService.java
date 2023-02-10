package fr.ajc.securityExoFinal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.models.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	// Permet de spécifier à Spring security comment gérer les utilisateurs
	
	private CustomUserService customUserService;

	public CustomUserDetailsService(CustomUserService customUserService) {
		this.customUserService = customUserService;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser user = customUserService.getByUsername(username);
		return new User(user.getUsername(), user.getPassword(), rolesToAuthority(user.getRoles()));
	}
	// Le User est un User au sens de Spring Security --> on transforme une instance de CustomUser en User
	// GrantedAuthority --> droits donnés à un User pour aller sur certaines routes
	
	private List<GrantedAuthority> rolesToAuthority(List<CustomRole> roles){
		List<GrantedAuthority> authorities= new ArrayList<>();
		for (CustomRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}
}
