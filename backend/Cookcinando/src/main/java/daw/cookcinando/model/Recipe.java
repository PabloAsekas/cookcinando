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
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description;
	private String thumbnail; //Image
	
	@Column(length=1000000000)
	private String preparation; //Cuerpo
	
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> ingredients = new ArrayList<String>();
	
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> typesFood = new ArrayList<String>();
	
	//private User author;
	
	protected Recipe(){}
	
	public Recipe (String title, String description, String thumbnail, String preparation, 
			       List<String> ingredients, List<String> typesFood) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail; //Image
		this.preparation = preparation; //Cuerpo
		this.ingredients = ingredients;
		this.typesFood = typesFood;
		//this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getTypesFood() {
		return typesFood;
	}

	public void setTypesFood(List<String> typesFood) {
		this.typesFood = typesFood;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", description=" + description + ", thumbnail=" + thumbnail
				+ ", preparation=" + preparation + ", ingredients=" + ingredients + ", typesFood=" + typesFood + "]";
	}
}