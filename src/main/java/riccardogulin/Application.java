package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.BlogPost;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13");

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);

		User aldo = new User("Aldo", "Baglio");
		User giovanni = new User("Giovanni", "Storti");
		User giacomo = new User("Giacomo", "Poretti");

		// ***************************************** 1to1 *****************************************

		// Document aldoDoc = new Document("aojdosjadoj", "Italy", aldo);
		// Non posso utilizzare aldo in questo caso perché non fa parte del Persistence Context ed è un oggetto Java normalissimo
		// Solo oggetti che o hanno ricevuto un .persist() oppure sono letti dal db possono essere utilizzati
		User aldoFromDB = ud.findById("820758ef-4078-4b31-94ec-e28a6581a653");
		Document aldoDoc = new Document("aojdosjadoj", "Italy", aldoFromDB);
		// dd.save(aldoDoc);

		System.out.println(aldoFromDB.getDocument());

		// ***************************************** 1toMany *****************************************
		BlogPost react = new BlogPost("React", "Non può essere bello in quanto frontend", aldoFromDB);
		// bd.save(react);

		BlogPost javaFromDB = bd.findById("b8bfd0da-bc1b-4c4a-82a0-73f02231adba");
		System.out.println(javaFromDB);

		System.out.println("------------ BIDIREZIONALITA'----------------");
		aldoFromDB.getBlogPostsList().forEach(System.out::println);

		em.close();
		emf.close();
	}
}
