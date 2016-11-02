package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section extends DomainEntity {
	
	//Constructor--------------------
	public Section() {
		super();
	}
	
	//Attributes---------------------
	private String title, text;
	
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	//Relationships----------------------
	private WhitePaper whitePaper;
	
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public WhitePaper getWhitePaper() {
		return whitePaper;
	}

	public void setWhitePaper(WhitePaper whitePaper) {
		this.whitePaper = whitePaper;
	}
	
	
	
	
	
	
	
	
	

}
