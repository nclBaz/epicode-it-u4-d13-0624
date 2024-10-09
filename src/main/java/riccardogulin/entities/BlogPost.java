package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
	@Id
	@GeneratedValue
	private UUID id;
	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User author;

	@ManyToMany
	@JoinTable(name = "blogs_categories",
			joinColumns = @JoinColumn(name = "blog_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false)
	) // E' importante la @JoinTable per definire come sarà fatta (nome e le due colonne) la JUNCTION TABLE.
	// Se lascio fare a Hibernate farà pasticci
	private List<Category> categories;

	public BlogPost() {
	}

	public BlogPost(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "BlogPost{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", author=" + author +
				'}';
	}
}