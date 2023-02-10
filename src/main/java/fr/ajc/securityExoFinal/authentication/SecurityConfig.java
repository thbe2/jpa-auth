package fr.ajc.securityExoFinal.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;
    
//    @Bean
//    public String h2ConsolePath() {
//    return "/h2-ui";
//    }
    
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.disable())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/register/**", "", "/").permitAll()
//                        .requestMatchers(h2ConsolePath + "/**").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/details/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form.permitAll())
//                .logout(logout -> logout.permitAll())
//                .userDetailsService(userDetailsService)
//                .headers(headers -> headers.frameOptions().sameOrigin());
//        return http.build();
//    }
}
