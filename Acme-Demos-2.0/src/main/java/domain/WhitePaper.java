package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class WhitePaper extends DomainEntity {
	
	//Constructor---------------------------------
	public WhitePaper() {
		super();
	}
	
	//Attributes-----------------------------------
	private String title, abstractReview;
	private Date publishedDate;
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getAbstractReview() {
		return abstractReview;
	}
	public void setAbstractReview(String abstractReview) {
		this.abstractReview = abstractReview;
	}
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	
	//Relationships-----------------------------
	private Investor investor;
	private Collection<Section> sections;
	
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Investor getInvestor() {
		return investor;
	}
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
	
	
	@NotNull
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="whitePaper")
	public Collection<Section> getSections() {
		return sections;
	}
	public void setSections(Collection<Section> sections) {
		this.sections = sections;
	}
	
	
	
	
	
	

}
