package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Instalment extends DomainEntity {

	// Constructor-----------------
	public Instalment() {
		super();
	}

	// Attributes-----------------
	private Date instalmentDate;
	private Double amount;
	private Boolean paid;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	public Date getInstalmentDate() {
		return instalmentDate;
	}

	public void setInstalmentDate(Date instalmentDate) {
		this.instalmentDate = instalmentDate;
	}

	@Min(1)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	// Relationships--------------------
	private Investment investment;
	private Loan loan;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}
