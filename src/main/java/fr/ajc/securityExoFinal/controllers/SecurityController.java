package fr.ajc.securityExoFinal.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.ajc.securityExoFinal.services.CustomUserServiceInterface;

@RestController
@RequestMapping("/")
public class SecurityController {
	
	private static final String LIST_USER = "list-user";
	private static final String USER = "user";
	
	private CustomUserServiceInterface customUserServiceInterface;

	public SecurityController(CustomUserServiceInterface customUserServiceInterface) {
		this.customUserServiceInterface = customUserServiceInterface;
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
	
	
}
