package daw.cookcinando.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/api/**");
		
		// URLs that need authentication to access to it
		
		//Events
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/events/").hasAnyRole("ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/events/{id}").hasAnyRole("ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/events/{id}").hasAnyRole("ENTERPRISE", "ADMIN");
		
		//Recipes
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/recipes/").hasAnyRole("BASIC", "ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/recipes/{id}").hasAnyRole("BASIC", "ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/recipes/{id}").hasAnyRole("BASIC", "ENTERPRISE", "ADMIN");
		
		//Restaurants
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/restaurants/").hasAnyRole("ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/restaurants/{id}").hasAnyRole("ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/restaurants/{id}").hasAnyRole("ENTERPRISE", "ADMIN");
		
		//Users
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/").hasRole("ADMIN");
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasRole("BASIC");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{id}").hasAnyRole("BASIC", "ENTERPRISE", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyRole("BASIC", "ENTERPRISE", "ADMIN");
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();
		
		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();
		
		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}

}
