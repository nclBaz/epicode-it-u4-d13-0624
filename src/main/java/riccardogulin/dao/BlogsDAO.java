package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.BlogPost;
import riccardogulin.exceptions.NotFoundException;

import java.util.UUID;

public class BlogsDAO {
	private final EntityManager em;

	public BlogsDAO(EntityManager em) {
		this.em = em;
	}

	public void save(BlogPost blogPost) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(blogPost);
		transaction.commit();
		System.out.println("Il blog " + blogPost.getTitle() + " è stato salvato correttamente!");
	}

	public BlogPost findById(String blogId) {
		BlogPost found = em.find(BlogPost.class, UUID.fromString(blogId));
		if (found == null) throw new NotFoundException(blogId);
		return found;
	}
}
