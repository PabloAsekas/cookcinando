package daw.cookcinando.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String surname;
	private String description;
	private String image;
	private String nick;
	private String email;
	private String password;
	
	//private Favorites favorites;
//	private List<Recipe> recipes;
//	private List<Restaurants> restaurants;
//	private List<Events> events;
	
	protected User(){}
	
	public User(String name, String surname, String description, String image, String nick,
			    String email, String password){
		super();
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.image = image;
		this.nick = nick;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", description=" + description
				+ ", image=" + image + ", nick=" + nick + ", email=" + email + ", password=" + password + "]";
	}
}