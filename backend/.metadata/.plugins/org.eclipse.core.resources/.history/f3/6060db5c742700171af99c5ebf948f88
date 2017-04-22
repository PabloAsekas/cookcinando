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
public class UserBasic extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	protected UserBasic(){}
	
	public UserBasic(String name, String surname, String description, String image, String nick,
		    		 String email, String password,String... roles){
		
		super(name,surname,description,image,nick,email,password,roles);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserBasic [id=" + id + "]";
	}
}
