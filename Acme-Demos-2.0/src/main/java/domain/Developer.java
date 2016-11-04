package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Developer extends Actor {

	// Constructors...................
	public Developer() {
		super();
	}

	// Attributes ----------------------------------------------------------

	// Relationships --------------------------------------------------------
	private Collection<Demo> demos;
	private Collection<Loan> loans;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "developer")
	public Collection<Demo> getDemos() {
		return demos;
	}

	public void setDemos(Collection<Demo> demos) {
		this.demos = demos;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "developer")
	public Collection<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Collection<Loan> loans) {
		this.loans = loans;
	}

}