package fr.ajc.securityExoFinal.services;

import java.util.List;

import fr.ajc.securityExoFinal.models.CustomUser;

public interface CustomUserServiceInterface {
	
	CustomUser getByUsername(String username);
	
	CustomUser addUser(CustomUser user);
	
	List<CustomUser> getAllUsers();
	
	CustomUser getUserById(Long id);

}
