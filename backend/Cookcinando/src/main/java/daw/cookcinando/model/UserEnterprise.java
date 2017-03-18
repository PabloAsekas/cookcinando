package daw.cookcinando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEnterprise extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	@OneToMany(mappedBy="author")
	private List<Restaurant> myRestaurants;
	
	@Column
	@OneToMany(mappedBy="author")
	private List<Event> myEvents;
	
	protected UserEnterprise(){}
	
	public UserEnterprise(String name, String surname, String description, String image, String nick,
		    		 	  String email, String password, String...roles){
		
		super(name,surname,description,image,nick,email,password,roles);
		this.myRestaurants = new ArrayList<Restaurant>();
		this.myEvents = new ArrayList<Event>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Restaurant> getMyRestaurants() {
		return myRestaurants;
	}

	public void setMyRestaurants(List<Restaurant> myRestaurants) {
		this.myRestaurants = myRestaurants;
	}

	public List<Event> getMyEvents() {
		return myEvents;
	}

	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}

	@Override
	public String toString() {
		return "UserEnterprise [id=" + id + ", myRestaurants=" + myRestaurants + ", myEvents=" + myEvents + "]";
	}
}
