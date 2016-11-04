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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Loan extends DomainEntity {

	// Constructor--------------------------
	public Loan() {
		super();
	}

	// Attributes---------------------------
	private String status;
	private Date requestMoment, processMoment;
	private Double amount;

	// Values ------------------------------------------------------------------
	public static final String APPROVED = "APPROVED";
	public static final String DENIED = "DENIED";
	public static final String PENDING = "PENDING";

	// Getters & Setters--------------------------------------------------------
	@NotBlank
	@Pattern(regexp = "^" + APPROVED + "|" + DENIED + "|" + PENDING + "$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	public Date getRequestMoment() {
		return requestMoment;
	}

	public void setRequestMoment(Date requestMoment) {
		this.requestMoment = requestMoment;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getProcessMoment() {
		return processMoment;
	}

	public void setProcessMoment(Date processMoment) {
		this.processMoment = processMoment;
	}

	@NotNull
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	// Relationships------------------------------------------
	private Bank bank;
	private Developer developer;
	private Collection<Instalment> instalments;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

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
	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "loan")
	public Collection<Instalment> getInstalments() {
		return instalments;
	}

	public void setInstalments(Collection<Instalment> instalments) {
		this.instalments = instalments;
	}

}
