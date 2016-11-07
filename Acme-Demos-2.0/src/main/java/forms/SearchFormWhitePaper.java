package forms;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Access(AccessType.PROPERTY)
public class SearchFormWhitePaper {

	private String text;
	private Date dateFirst;
	private Date dateSecond;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateFirst() {
		return dateFirst;
	}

	public void setDateFirst(Date dateFirst) {
		this.dateFirst = dateFirst;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateSecond() {
		return dateSecond;
	}

	public void setDateSecond(Date dateSecond) {
		this.dateSecond = dateSecond;
	}

}
