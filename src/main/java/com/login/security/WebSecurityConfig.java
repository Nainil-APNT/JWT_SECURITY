package com.login.security;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.login.security.jwt.AuthEntryPointJwt;
import com.login.security.jwt.AuthTokenFilter;
import com.login.security.service.UserDetailsServiceImpl;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{ 
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }


  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }


  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
          auth
          .shouldFilterAllDispatcherTypes(false)
          .requestMatchers("/api/auth/**").permitAll()
              .requestMatchers("/api/test/**").permitAll()
              .requestMatchers("/applicationdetails/**").permitAll()          
            //  .requestMatchers("/employee/**").permitAll()
              
              .requestMatchers("/employee/create").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
              .requestMatchers("/employee/list").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") 
              .requestMatchers("/employee/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
              

              .requestMatchers("/applicant/create").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
              .requestMatchers("/applicant/list").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") 
              .requestMatchers("/applicant/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
              
              .requestMatchers("/company/create").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
              .requestMatchers("/company/list").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") 
              .requestMatchers("/company/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
              
              .requestMatchers("/jobinformation/create").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
              .requestMatchers("/jobinformation/list").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") 
              .requestMatchers("/jobinformation/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
              
              .requestMatchers("/jobcategory/create").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
              .requestMatchers("/jobcategory/list").hasAnyAuthority("ROLE_ADMIN","ROLE_USER") 
              .requestMatchers("/jobcategory/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
              
              .anyRequest().authenticated()
        );
    
    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }
  
  @Bean
	public FilterRegistrationBean<?> corsFilter(){
	UrlBasedCorsConfigurationSource source =	new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	config.addAllowedOrigin("http://localhost:3000");
	config.setAllowedHeaders(Arrays.asList(

			HttpHeaders.AUTHORIZATION,
			HttpHeaders.CONTENT_TYPE,
			HttpHeaders.ACCEPT
			
			));
		
		config.setAllowedMethods(Arrays.asList(

				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()
				));
			config.setMaxAge(3600L);
			source.registerCorsConfiguration("/**", config);
			
			FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
			bean.setOrder(-102);
	return bean;
	
	}
	
 

}
