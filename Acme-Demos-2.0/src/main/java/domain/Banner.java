package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	public Banner() {
		super();
	}
	
	private String link;
	
	@URL
	@NotBlank
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	//Relationships---------------------------
	private Investor investor;
	private Collection<Demo> demos;
	
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
	
	@Valid
	@NotNull
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="banner")
	public Collection<Demo> getDemos() {
		return demos;
	}

	public void setDemos(Collection<Demo> demos) {
		this.demos = demos;
	}
	
	
	
	
	
	
	

}
