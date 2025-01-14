package com.scm.config;

import com.scm.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	// @Bean
//	public UserDetailsService userDetailsService() {

	/*
	 * UserDetails user1 =
	 * User.withDefaultPasswordEncoder().username("admin123").password("admin123")
	 * .roles("ADMIN", "USER").build(); UserDetails user2 =
	 * User.withDefaultPasswordEncoder().username("user123").password("user123").
	 * roles("USER") .build();
	 */

	/*
	 * var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
	 * user2); return inMemoryUserDetailsManager; }
	 */
	@Autowired
	private SecurityCustomUserDetailService securityCustomUserDetailService;
	
	@Autowired
	private OAuthAuthenticationSuccessHandler handler;

	// Configuration of authentication provider for spring security
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		// user detail service object
		daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
		// password encoder object
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		// configuration
		//configured urls which becomes public or private
		httpSecurity.authorizeHttpRequests(authorize -> {
//			authorize.requestMatchers("/home","/register","/services").permitAll();
			authorize.requestMatchers("/user/**").authenticated();
			authorize.anyRequest().permitAll();
		});

		//form default login
		//if we have to change anything related to form login we do here
		httpSecurity.formLogin(formLogin->{
			
			formLogin.loginPage("/login")
			.loginProcessingUrl("/authenticate")
			.successForwardUrl("/user/profile")
//			.failureForwardUrl("/login?error=true")
//			.defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password");
//			.failureHandler(new AuthenticationFailureHandler() {
				
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						AuthenticationException exception) throws IOException, ServletException {
//					// TODO Auto-generated method stub
//					
//				}
//			})
//			.successHandler(new AuthenticationSuccessHandler() {
//				
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//						Authentication authentication) throws IOException, ServletException {
//					// TODO Auto-generated method stub
//					
//				}
//			});
		});
		
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.logout(logoutForm->{
			logoutForm.logoutUrl("/do-logout")
			.logoutSuccessUrl("/login?logout=true");
		});
		
		//oauth configurations
		httpSecurity.oauth2Login(oauth->{
			oauth.loginPage("/login");
			oauth.successHandler(handler);
		});
		
		
		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
