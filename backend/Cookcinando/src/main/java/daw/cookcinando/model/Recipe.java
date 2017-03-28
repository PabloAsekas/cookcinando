package daw.cookcinando.model;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Recipe {
	
	public interface Basic { }
	
	public interface Users { }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Basic.class)
	private long id;
	
	@JsonView(Basic.class)
	private String title;
	@JsonView(Basic.class)
	private String description;
	@JsonView(Basic.class)
	private String thumbnail; //Image
	
	@Column(length=1000000000)
	@JsonView(Basic.class)
	private String preparation; //Cuerpo
	
	@Column
	@ElementCollection(targetClass=String.class)
	@JsonView(Basic.class)
	private List<String> ingredients = new ArrayList<String>();
	
	@Column
	@ElementCollection(targetClass=String.class)
	@JsonView(Basic.class)
	private List<String> typesFood = new ArrayList<String>();
	
	@ManyToOne
	@JsonView(Users.class)
	private User author;
	
	protected Recipe(){}
	
	public Recipe (String title, String description, String thumbnail, String preparation, 
			       List<String> ingredients, List<String> typesFood, User author) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail; //Image
		this.preparation = preparation; //Cuerpo
		this.ingredients = ingredients;
		this.typesFood = typesFood;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", description=" + description + ", thumbnail=" + thumbnail
				+ ", preparation=" + preparation + ", ingredients=" + ingredients + ", typesFood=" + typesFood
				+ ", author=" + author + "]";
	}
}