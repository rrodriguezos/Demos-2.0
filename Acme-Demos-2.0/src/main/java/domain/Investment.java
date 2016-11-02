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
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Investment extends DomainEntity {

	
	
	//Constructor------------------------
	public Investment() {
		super();
	}
	
	//Attributes--------------------------
	private Date approvalMoment;
	private String description;
	
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getApprovalMoment() {
		return approvalMoment;
	}
	public void setApprovalMoment(Date approvalMoment) {
		this.approvalMoment = approvalMoment;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//Relationships----------------------------------
	private Investor investor;
	private Demo demo;
	private Collection<Instalment> instalments;
	private Bank bank;

	
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
	@ManyToOne(optional = false)
	public Demo getDemo() {
		return demo;
	}
	public void setDemo(Demo demo) {
		this.demo = demo;
	}
	

	@Valid
	@NotNull
	@OneToMany(cascade={CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},mappedBy="investment")
	public Collection<Instalment> getInstalments() {
		return instalments;
	}
	public void setInstalments(Collection<Instalment> instalments) {
		this.instalments = instalments;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = true)
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
	
	
	
	

}
