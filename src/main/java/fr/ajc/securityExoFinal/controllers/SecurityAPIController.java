package fr.ajc.securityExoFinal.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.services.CustomRoleServiceInterface;
import fr.ajc.securityExoFinal.services.CustomUserServiceInterface;

@RestController
@RequestMapping("/api")
public class SecurityAPIController {

	private CustomUserServiceInterface customUserServiceInterface;
	private CustomRoleServiceInterface customRoleServiceInterface;
	private PasswordEncoder passwordEncoder;

	public SecurityAPIController(CustomUserServiceInterface customUserServiceInterface,
			CustomRoleServiceInterface customRoleServiceInterface, PasswordEncoder passwordEncoder) {
		this.customUserServiceInterface = customUserServiceInterface;
		this.customRoleServiceInterface = customRoleServiceInterface;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/users")
	public List<CustomUser> getUsers(@RequestParam(required = false) Long id) {
		List<CustomUser> users = new ArrayList<>();
		if (Objects.isNull(id)) {
			users = customUserServiceInterface.getAllUsers();
			return users;
		}
		users.add(customUserServiceInterface.getUserById(id));
		return users;
	}

	@GetMapping("/me")
	public String currentUserName(Principal principal) {
		return principal.getName();
	}

	@PostMapping("/add-user")
	public CustomUser addUser(@RequestBody CustomUser user) {
		CustomRole role = customRoleServiceInterface.getByRoleName("ROLE_USER");
		user.setRoles(List.of(role));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return customUserServiceInterface.addUser(user);
	}

	@PatchMapping("/change-role")
	public CustomUser changeRole(@RequestParam Long id, @RequestBody List<CustomRole> roles) {
		return customUserServiceInterface.updateRole(id, roles);
	}
}
