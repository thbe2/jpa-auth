package fr.ajc.securityExoFinal.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.repositories.CustomRoleRepository;

@Service
public class CustomRoleService implements CustomRoleServiceInterface {

	private CustomRoleRepository customRoleRepository;

	public CustomRoleService(CustomRoleRepository customRoleRepository) {
		this.customRoleRepository = customRoleRepository;
	}

	@Override
    public CustomRole getByRoleName(String roleName) throws IllegalArgumentException {
        return customRoleRepository.findByRoleName(roleName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Role %s was not found!", roleName))
        );
    }

}
