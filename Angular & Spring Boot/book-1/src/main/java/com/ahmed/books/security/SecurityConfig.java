package com.ahmed.books.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Token cross-Site Request Forgery
		http.csrf().disable();
		//Ne pas enregistrer les seassions de travail chaque fois
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers("/login").permitAll();
		
		//
		http.authorizeRequests().antMatchers("/user/all").hasAuthority("Admin");
		///
		
		http.authorizeRequests().antMatchers("/apig/**").hasAnyAuthority("Admin","User");
		
		
		
		 //consulter tous les livres
		http.authorizeRequests().antMatchers("/api/all/**").hasAnyAuthority("Admin","User");
		//consulter un livre par son id
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority("Admin","User");
		//ajouter un nouveau livre
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/**").hasAuthority("Admin");
		//modifier un livre
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/**").hasAuthority("Admin");
		//supprimer un livre
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("Admin");
		
		
		 //consulter tous les genres
		http.authorizeRequests().antMatchers("/apig/all/**").hasAnyAuthority("Admin","User");
		//consulter un genre par son id
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/apig/**").hasAnyAuthority("Admin","User");
		//ajouter un nouveau genre
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/apig/**").hasAuthority("Admin");
		//modifier un genre
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/apig/**").hasAuthority("Admin");
		//supprimer un genre
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/apig/**").hasAuthority("Admin");
		 
		
		
		http.authorizeRequests().anyRequest().authenticated();
		//http.authorizeRequests().anyRequest().permitAll();
		

		
		
		//Sayd fil book
		http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
		
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		
		

		
		

	}
}