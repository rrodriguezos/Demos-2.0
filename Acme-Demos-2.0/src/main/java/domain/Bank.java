package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Bank extends Actor {

	// Constructor.........................
	public Bank() {
		super();
	}

	// Attributes...........................
	private String commercialName, SWIFTCode;

	@NotBlank
	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$")
	@NotBlank
	public String getSWIFTCode() {
		return SWIFTCode;
	}

	public void setSWIFTCode(String sWIFTCode) {
		SWIFTCode = sWIFTCode;
	}

	// Relationships---------------------------------
	private Collection<Investment> investments;
	private Collection<Loan> loans;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "bank")
	public Collection<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(Collection<Investment> investments) {
		this.investments = investments;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "bank")
	public Collection<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Collection<Loan> loans) {
		this.loans = loans;
	}

}
