package daw.cookcinando.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/recetas").permitAll();
		http.authorizeRequests().antMatchers("/recetas/{id}").permitAll();
		
		//Private pages
		http.authorizeRequests().antMatchers("/privado/recetas/crear").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
//		http.authorizeRequests().antMatchers("/privado/recetas/form-crear").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
//		http.authorizeRequests().antMatchers("/privado/recetas/editar/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
//		http.authorizeRequests().antMatchers("/privado/recetas/form-editar/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
//		http.authorizeRequests().antMatchers("/privado/todos-usuarios").hasAnyRole("ADMIN");
		
		//Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		
		// Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

		http.csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
		
		//USERS
//		auth.inMemoryAuthentication().withUser("basic").password("pass").roles("BASIC");
//		auth.inMemoryAuthentication().withUser("enterprise").password("pass").roles("BASIC","ENTERPRISE");
//		auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles("BASIC","ENTERPRISE","ADMIN");
	}

	
}
