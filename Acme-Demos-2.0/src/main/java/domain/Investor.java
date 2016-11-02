package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Investor extends Actor {
	// Constructors...................
	public Investor() {
		super();
	}

	// Attributes ----------------------------------------------------------
	private String company;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// Relationships --------------------------------------------------------
	private Collection<Investment> investments;
	private Collection<WhitePaper> whitePapers;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "investor")
	public Collection<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(Collection<Investment> investments) {
		this.investments = investments;
	}
	
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "investor")
	public Collection<WhitePaper> getWhitePapers() {
		return whitePapers;
	}

	public void setWhitePapers(Collection<WhitePaper> whitePapers) {
		this.whitePapers = whitePapers;
	}
	
	
	

}
