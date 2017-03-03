package daw.cookcinando.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserBasic extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	@ElementCollection(targetClass=String.class)
	private List<Recipe> myRecipes;
	
	protected UserBasic(){}
	
	public UserBasic(String name, String surname, String description, String image, String nick,
		    		 String email, String password){
		super(name,surname,description,image,nick,email,password);
		this.myRecipes = new ArrayList<Recipe>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Recipe> getMyRecipes() {
		return myRecipes;
	}

	public void setMyRecipes(List<Recipe> myRecipes) {
		this.myRecipes = myRecipes;
	}

	@Override
	public String toString() {
		return "UserBasic [id=" + id + ", myRecipes=" + myRecipes + "]";
	}
}
