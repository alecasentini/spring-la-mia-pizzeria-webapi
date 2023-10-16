package org.java.app.mvc.auth.config;

import org.java.app.mvc.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
	throws Exception {
			http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1.0/**").permitAll()
	        .requestMatchers("/user/**").hasAuthority("USER")
	        .requestMatchers("/admin/**").hasAuthority("ADMIN")
	        .requestMatchers("/pizzas/create").hasAuthority("ADMIN")
	        .requestMatchers("/pizzas/update/**").hasAuthority("ADMIN")
	        .requestMatchers("/pizzas/delete/**").hasAuthority("ADMIN")
	        .requestMatchers("/ingredients/**").hasAnyAuthority("ADMIN")
	        .requestMatchers("/pizzas/*/special-offer/create").hasAnyAuthority("ADMIN")
	        .requestMatchers("/pizzas/**").hasAnyAuthority("USER", "ADMIN")
	        .requestMatchers("/**").permitAll()
	        .and().formLogin().defaultSuccessUrl("/pizzas")
	        .and().logout();
	
		return http.build();
	}

	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

    @Bean
    PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
    }
}
