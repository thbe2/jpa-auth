package fr.ajc.securityExoFinal.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.services.CustomRoleServiceInterface;
import fr.ajc.securityExoFinal.services.CustomUserServiceInterface;

@RestController
@RequestMapping("/")
public class SecurityController {
	
	private static final String LIST_USER = "list-user";
	private static final String USER = "user";
	private static final String ME = "me";
	private static final String ADD_FORM = "add-form";
	private static final String USER_ADDED = "user-added";
	
	private CustomUserServiceInterface customUserServiceInterface;
	private PasswordEncoder passwordEncoder;
	private CustomRoleServiceInterface customRoleServiceInterface;

	public SecurityController(CustomUserServiceInterface customUserServiceInterface, PasswordEncoder passwordEncoder,
			CustomRoleServiceInterface customRoleServiceInterface) {
		this.customUserServiceInterface = customUserServiceInterface;
		this.passwordEncoder = passwordEncoder;
		this.customRoleServiceInterface = customRoleServiceInterface;
	}

	@GetMapping("users")
	public ModelAndView getUsers(@RequestParam(required = false) Long id) {
		ModelAndView mav = new ModelAndView(LIST_USER);
		if (id == null) {
			return mav.addObject("users",customUserServiceInterface.getAllUsers());
		}
		mav.setViewName(USER);
		mav.addObject("user", customUserServiceInterface.getUserById(id));
		return mav;
}
	
	@GetMapping("me")
	public ModelAndView getMe(Principal principal) {
		ModelAndView mav = new ModelAndView(ME);
		mav.addObject("me", principal.getName());
		return mav;
}
	
	@GetMapping("/add")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView(ADD_FORM);
		mav.addObject("user", new CustomUser());
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView saveUser(@ModelAttribute CustomUser user) {
		ModelAndView mav = new ModelAndView(USER_ADDED);
		// Hash du mot de passe
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Récupération du rôle USER
		CustomRole roleUser = customRoleServiceInterface.getByRoleName("ROLE_USER");
		// Créer le rôle s'il n'existe pas
		if (Objects.isNull(roleUser)) {
            roleUser = new CustomRole("ROLE_USER");
        }
        // Attribution du rôle à l'utilisateur
        user.setRoles(List.of(roleUser));
        // Ajout de l'user en bdd
		customUserServiceInterface.addUser(user);
		// Retour de la vue
		mav.addObject("user", user);
		return mav;
	}
}
