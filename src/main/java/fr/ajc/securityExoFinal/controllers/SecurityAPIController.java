package fr.ajc.securityExoFinal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.services.CustomUserService;

@RestController
@RequestMapping("/api")
public class SecurityAPIController {

	private CustomUserService customUserService;

	public SecurityAPIController(CustomUserService customUserService) {
		this.customUserService = customUserService;
	}

	@GetMapping("/users")
	public List<CustomUser> getUsers(@RequestParam(required = false) Long id) {
		List<CustomUser> users = new ArrayList<>();
		if (Objects.isNull(id)) {
			users = customUserService.getAllUsers();
			return users;
		}
		users.add(customUserService.getUserById(id));
		return users;
	}

}
