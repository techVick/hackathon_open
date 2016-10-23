package ge.dt.service.analyticsapp.dto;

import ge.dt.service.analyticsapp.json.JsonDateDeSerializer;
import ge.dt.service.analyticsapp.json.JsonDateSerializer;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IssuePlanDto {

	private long id;

	private long serial;

	private String status;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date compliedOn;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date dueOn;

	@JsonBackReference("issue-issuplan")
	private IssueDto issue;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
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

	public IssueDto getIssue() {
		return issue;
	}

	public void setIssue(IssueDto issue) {
		this.issue = issue;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (serial ^ (serial >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssuePlanDto other = (IssuePlanDto) obj;
		if (serial != other.serial)
			return false;
		return true;
	}

}
