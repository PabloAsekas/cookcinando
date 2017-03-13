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
		http.authorizeRequests().antMatchers("/login-error").permitAll();
		http.authorizeRequests().antMatchers("/registro").permitAll();
		
		http.authorizeRequests().antMatchers("/usuario/{id}").permitAll();
		
		http.authorizeRequests().antMatchers("/recetas").permitAll();
		http.authorizeRequests().antMatchers("/recetas/{id}").permitAll();
		http.authorizeRequests().antMatchers("/moreRecipes").permitAll();
		
		http.authorizeRequests().antMatchers("/eventos").permitAll();
		http.authorizeRequests().antMatchers("/eventos/{id}").permitAll();
		http.authorizeRequests().antMatchers("/moreEvents").permitAll();
		
		http.authorizeRequests().antMatchers("/restaurantes").permitAll();
		http.authorizeRequests().antMatchers("/restaurantes/{id}").permitAll();
		http.authorizeRequests().antMatchers("/moreRestaurants").permitAll();
		
		//Private pages
		http.authorizeRequests().antMatchers("/privado/mi-cuenta").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/todos-usuarios").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/privado/mis-favoritos").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/ajustes").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/ajustar-cuenta/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/modificar-cuenta/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		
		http.authorizeRequests().antMatchers("/privado/mis-recetas").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/crear").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/form-crear").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/editar/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/form-editar/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/eliminar/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/add-fav/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/recetas/remove-fav/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/todas-recetas").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers("/privado/eventos/mis-eventos").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/crear").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/form-crear").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/editar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/form-editar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/eliminar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/add-fav/{id}").hasAnyRole("BASIC", "ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/remove-fav/{id}").hasAnyRole("BASIC", "ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/eventos/todas-eventos").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers("/privado/restaurantes/mis-restaurantes").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/crear").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/form-crear").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/editar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/form-editar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/eliminar/{id}").hasAnyRole("ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/add-fav/{id}").hasAnyRole("BASIC", "ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/remove-fav/{id}").hasAnyRole("BASIC","ENTERPRISE","ADMIN");
		http.authorizeRequests().antMatchers("/privado/restaurantes/todos-restaurantes").hasAnyRole("ADMIN");
		
		//Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/privado/mi-cuenta");
		 http.formLogin().failureUrl("/login-error");
		 
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
