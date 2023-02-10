package fr.ajc.securityExoFinal.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.repositories.CustomUserRepository;

@Service
public class CustomUserService {

	private CustomUserRepository customUserRepository;

	public CustomUserService(CustomUserRepository customUserRepository) {
		this.customUserRepository = customUserRepository;
	}
	
	public CustomUser getByUsername(String username) {
		return customUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found!", username)));
	}
}
