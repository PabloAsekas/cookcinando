package daw.cookcinando.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class User {

	public interface Basic { }
	
	public interface Recipes { }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@JsonView(Basic.class)
	private long id;
	
	@JsonView(Basic.class)
	private String name;
	@JsonView(Basic.class)
	private String surname;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String image;
	@JsonView(Basic.class)
	private String nick;
	@JsonView(Basic.class)
	private String email;
	@JsonView(Basic.class)
	private String passwordHash;
	
	@JsonView(Basic.class)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@Column
	@OneToMany(mappedBy="author")
	@JsonView(Recipes.class)
	private List<Recipe> myRecipes;
	
	@OneToMany
	@JsonView(Recipes.class)
	private List<Recipe> favRecipes;
	
	@JsonIgnore
	@OneToMany
	private List<Restaurant> favRestaurants;
	
	@JsonIgnore
	@OneToMany
	private List<Event> favEvents;
	
	protected User(){}
	
	public User(String name, String surname, String description, String image, String nick,
			    String email, String password, String... roles){
		super();
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.image = image;
		this.nick = nick;
		this.email = email;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.myRecipes = new ArrayList<Recipe>();
		this.favRecipes = new ArrayList<Recipe>();
		this.favRestaurants = new ArrayList<Restaurant>();
		this.favEvents = new ArrayList<Event>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Recipe> getMyRecipes() {
		return myRecipes;
	}

	public void setMyRecipes(List<Recipe> myRecipes) {
		this.myRecipes = myRecipes;
	}
	
	public List<Recipe> getFavRecipes() {
		return favRecipes;
	}
	
	public void setFavRecipes(List<Recipe> favRecipes) {
		this.favRecipes = favRecipes;
	}	

	public List<Restaurant> getFavRestaurants() {
		return favRestaurants;
	}

	public void setFavRestaurants(Restaurant restaurant) {
		this.favRestaurants.add(restaurant);
	}

	public List<Event> getFavEvents() {
		return favEvents;
	}
	
	public void setFavEvents(List<Event> favEvents) {
		this.favEvents = favEvents;
	}
	
	public boolean isAdmin() {
		return this.roles.contains("ROLE_ADMIN");
	}
	
	public boolean isEnterprise() {
		return this.roles.contains("ROLE_ENTERPRISE");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", description=" + description
				+ ", image=" + image + ", nick=" + nick + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", roles=" + roles + ", myRecipes=" + myRecipes + ", favRecipes=" + favRecipes + ", favRestaurants="
				+ favRestaurants + ", favEvents=" + favEvents + "]";
	}
}
