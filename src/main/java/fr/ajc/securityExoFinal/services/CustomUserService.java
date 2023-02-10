package fr.ajc.securityExoFinal.services;

import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.repositories.CustomUserRepository;

@Service
public class CustomUserService implements CustomUserServiceInterface {

	private CustomUserRepository customUserRepository;

	public CustomUserService(CustomUserRepository customUserRepository) {
		this.customUserRepository = customUserRepository;
	}

	@Override
	public CustomUser getByUsername(String username) {
		return customUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found!", username)));
	}

	@Override
	public List<CustomUser> getAllUsers() {
		return customUserRepository.findAll();
	}

	@Override
	public CustomUser getUserById(Long id) {
		return customUserRepository.findById(id).orElseThrow();
	}

	@Override
	public CustomUser addUser(CustomUser user) {
		return customUserRepository.save(user);
	}

	@Override
	public CustomUser updateRole(Long id, List<CustomRole> roles) {
		CustomUser customUser = getUserById(id);
		customUser.setRoles(roles);
		return addUser(customUser);
	}

}
