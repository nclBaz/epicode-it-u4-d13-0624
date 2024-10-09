package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue
	private UUID categoryId;
	private String name;

	@ManyToMany(mappedBy = "categories")
	private List<BlogPost> blogPostList;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public UUID getCategoryId() {
		return categoryId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BlogPost> getBlogPostList() {
		return blogPostList;
	}

	@Override
	public String toString() {
		return "Category{" +
				"categoryId=" + categoryId +
				", name='" + name + '\'' +
				'}';
	}
}
