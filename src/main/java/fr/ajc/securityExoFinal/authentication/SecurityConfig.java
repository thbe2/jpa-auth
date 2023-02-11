package fr.ajc.securityExoFinal.authentication;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.ajc.securityExoFinal.models.CustomRole;
import fr.ajc.securityExoFinal.models.CustomUser;
import fr.ajc.securityExoFinal.repositories.CustomUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
//		this.h2ConsolePath = h2ConsolePath;
	}

	/*@Value("${spring.h2.console.path}")
	private String h2ConsolePath;*/
	// private String h2ConsolePath = "h2-ui";

    @Bean
    String h2ConsolePath() {
        return "/h2-ui";
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// C'est notre encodeur
	// Si des problèmes avec h2 supprimer @Bean et mettre la méthode en static

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.cors(cors -> cors.disable())
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/register**").permitAll()
						.requestMatchers(h2ConsolePath() + "/**").permitAll()
						.requestMatchers("/api/users/**","/users/**").hasRole("ADMIN")
						.requestMatchers("/api/add-user/**").hasRole("ADMIN")
						.requestMatchers("/api/change-role/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						)
				.formLogin(form -> form.permitAll())
				.logout(logout -> logout.permitAll())
				.userDetailsService(userDetailsService)
				.headers(headers -> headers.frameOptions().sameOrigin());
		return http.build();
	}

	@Bean
    CommandLineRunner commandLineRunner(CustomUserRepository userRepository) {
    	return args -> {
    		CustomRole roleAdmin = new CustomRole("ROLE_ADMIN");
    		CustomRole roleUser = new CustomRole("ROLE_USER");
//    		CustomUser admin1 = new CustomUser("adminuser",passwordEncoder().encode("pass1"), List.of(roleAdmin, roleUser));
//    		userRepository.save(admin1);
    		CustomUser user1 = new CustomUser("user1",passwordEncoder().encode("user1"), List.of(roleUser));
    		userRepository.save(user1);
    	};
    	
    }
}
