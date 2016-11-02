package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"),
		@Index(columnList = "momentReleased") })
public class Demo extends DomainEntity {

	// Constructor ----------------------------------------------
	public Demo() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private Date momentReleased;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getMomentReleased() {
		return momentReleased;
	}

	public void setMomentReleased(Date momentReleased) {
		this.momentReleased = momentReleased;
	}

	// Relationships ---------------------------------------------------
	private Developer developer;
	private Collection<Resource> resource;
	private Collection<Comment> comments;
	private Collection<Description> descriptions;
	private Collection<Investment> investments;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	@Valid
	@NotNull
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="demo")
	public Collection<Resource> getResources() {
		return resource;
	}

	public void setResources(Collection<Resource> resource) {
		this.resource = resource;
	}

	@Valid
	@NotNull
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="demo")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="demo")
	public Collection<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}
	
	
	@Valid
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="demo")
	public Collection<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(Collection<Investment> investments) {
		this.investments = investments;
	}
	
	

}
