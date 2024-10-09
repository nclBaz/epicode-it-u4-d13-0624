package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.BlogsDAO;
import riccardogulin.dao.CategoriesDAO;
import riccardogulin.dao.DocumentsDAO;
import riccardogulin.dao.UsersDAO;
import riccardogulin.entities.BlogPost;
import riccardogulin.entities.Category;
import riccardogulin.entities.Document;
import riccardogulin.entities.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d13");

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		UsersDAO ud = new UsersDAO(em);
		DocumentsDAO dd = new DocumentsDAO(em);
		BlogsDAO bd = new BlogsDAO(em);
		CategoriesDAO cd = new CategoriesDAO(em);

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

		// ***************************************** ManytoMany *****************************************
		Category category = new Category("Backend");
		Category category2 = new Category("Frontend");
		Category category3 = new Category("OOP");
/*		cd.save(category);
		cd.save(category2);
		cd.save(category3);*/

		// Per assegnare ad un blog tot categorie dobbiamo
		// 1. Leggere le varie categorie dal db
		// 2. Creare una lista di categorie per quel blog
		// 3. Settargliele tramite setter
		// 4. Salvare il blog
		Category backendFromDB = cd.findById("7b8f1387-e82a-415e-b738-62a53c7ae77a");
		Category OOPFromDB = cd.findById("b63acb25-cdea-45d6-896a-ab9b48cfb1ab");

		javaFromDB.setCategories(new ArrayList<>(Arrays.asList(backendFromDB, OOPFromDB)));
		// bd.save(javaFromDB);

		System.out.println("Categorie del blog Java");
		javaFromDB.getCategories().forEach(System.out::println);

		System.out.println("Blog associati alla categoria backend");
		backendFromDB.getBlogPostList().forEach(System.out::println);


		em.close();
		emf.close();
	}
}
