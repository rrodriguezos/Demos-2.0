package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "text"), @Index(columnList = "isoCode") })
public class Description extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Description() {
		super();

	}

	// Attributes -------------------------------------------------------------

	private String text;

	private ISOCode isoCode;

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public ISOCode getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(ISOCode isoCode) {
		this.isoCode = isoCode;
	}

	// Relationships ----------------------------------------------------------
	private Demo demo;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Demo getDemo() {
		return demo;
	}

	public void setDemo(Demo demo) {
		this.demo = demo;
	}
}