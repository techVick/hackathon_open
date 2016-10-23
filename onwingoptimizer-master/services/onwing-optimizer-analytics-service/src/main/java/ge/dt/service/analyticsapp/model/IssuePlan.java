package ge.dt.service.analyticsapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "issuePlanSeq", initialValue = 101, allocationSize = 1)
public class IssuePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2384836817265527003L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issuePlanSeq")
	private long id;

	private long serial;

	private String status;

	private Date compliedOn;

	private Date dueOn;

	@ManyToOne(fetch = FetchType.LAZY)
	private Issue issue;

	private Date createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCompliedOn() {
		return compliedOn;
	}

	public void setCompliedOn(Date compliedOn) {
		this.compliedOn = compliedOn;
	}

	public Date getDueOn() {
		return dueOn;
	}

	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
