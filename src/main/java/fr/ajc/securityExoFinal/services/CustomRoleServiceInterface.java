package fr.ajc.securityExoFinal.services;

import fr.ajc.securityExoFinal.models.CustomRole;

public interface CustomRoleServiceInterface {
	
	CustomRole getByRoleName(String RoleName);
	
}
 