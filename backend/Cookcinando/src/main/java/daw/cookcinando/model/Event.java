package daw.cookcinando.model;

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

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description;
	private String thumbnail; //Image

	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> typesFood = new ArrayList<String>();

	
	//@Column
	@ElementCollection(targetClass=String.class)
	private List<String> courses = new ArrayList<String>();
	
	
	@ManyToOne
	private User author;
	
	protected Event(){}
	
	public Event (String title, String description, String thumbnail,List<String> typesFood, List<String> courses, User author) {
		super();
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail; //Image
		this.typesFood = typesFood;
		this.courses = courses;
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

	public List<String> getTypesFood() {
		return typesFood;
	}

	public void setTypesFood(List<String> typesFood) {
		this.typesFood = typesFood;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> setCourses) {
		this.courses = courses;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description=" + description + ", thumbnail=" + thumbnail
				+ ",typesFood=" + typesFood+ ", author=" + author + "]";
	}
}