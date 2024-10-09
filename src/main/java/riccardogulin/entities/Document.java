package riccardogulin.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {
	@Id
	@GeneratedValue
	@Column(name = "document_id")
	private UUID documentId;
	private String code;
	private String country;
	private LocalDate issueDate;
	private LocalDate expirationDate;

	@OneToOne
	// @OneToOne è OBBLIGATORIA se dichiaro un attributo di un tipo che rappresenta un'entità
	// User rappresenta un'entità pertanto se non metto @OneToOne otterrò
	// Could not determine recommended JdbcType for Java type 'riccardogulin.entities.User'
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	// JoinColumn serve per personalizzare la chiave esterna
	// nullable = false significa che non possiamo creare un documento senza user
	// unique = true significa che non ci potranno essere 2 documenti con lo stesso user
	private User user;

	public Document() {
	}

	public Document(String code, String country, User user) {
		this.code = code;
		this.country = country;
		this.issueDate = LocalDate.now();
		this.expirationDate = LocalDate.now().plusYears(10);
		this.user = user;
	}

	public UUID getDocumentId() {
		return documentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Document{" +
				"documentId=" + documentId +
				", code='" + code + '\'' +
				", country='" + country + '\'' +
				", issueDate=" + issueDate +
				", expirationDate=" + expirationDate +
				", user=" + user +
				'}';
	}
}
