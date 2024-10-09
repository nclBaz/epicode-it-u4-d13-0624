package riccardogulin.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
	@Id
	/*@GeneratedValue(strategy = GenerationType.AUTO)*/
	@GeneratedValue // <- Se non metto nessuna strategy sarà AUTO, quindi nel caso degli UUID mi genererà lui il codice univoco
	@Column(name = "user_id")
	private UUID userId;
	private String name;
	private String surname;

	// 1 to 1 BIDIREZIONALE
	@OneToOne(mappedBy = "user")
	// Se inserisco un riferimento su questo lato con tanto di @OneToOne
	// a Document, la relazione diventa bidirezionale
	// Ciò mi consente di, se cerco un utente nel db, di ottenere anche
	// i dati del suo documento tramite un getter getDocument
	// mappedBy serve per specificare il nome dell'ATTRIBUTO nell'altra entity
	// La bidirezionalità non crea nessuna nuova colonna nella tabella users!!
	private Document document;

	public User() {
	}

	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}

