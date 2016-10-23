package ge.dt.service.predixapp.dto;

import ge.dt.service.predixapp.json.JsonDateDeSerializer;
import ge.dt.service.predixapp.json.JsonDateSerializer;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IssueDto {

	private long id;

	private String name;

	private String description;

	private int expectedWindow;

	private String sbType;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date hardDate;

	private long cycle;

	private int interval;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date adDate;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date deadLineDate;

	@JsonManagedReference("issue-issuplan")
	private List<IssuePlanDto> issuePlans;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExpectedWindow() {
		return expectedWindow;
	}

	public void setExpectedWindow(int expectedWindow) {
		this.expectedWindow = expectedWindow;
	}

	public List<IssuePlanDto> getIssuePlans() {
		return issuePlans;
	}

	public void setIssuePlans(List<IssuePlanDto> issuePlans) {
		this.issuePlans = issuePlans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSbType() {
		return sbType;
	}

	public void setSbType(String sbType) {
		this.sbType = sbType;
	}

	public Date getHardDate() {
		return hardDate;
	}

	public void setHardDate(Date hardDate) {
		this.hardDate = hardDate;
	}

	public long getCycle() {
		return cycle;
	}

	public void setCycle(long cycle) {
		this.cycle = cycle;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Date getAdDate() {
		return adDate;
	}

	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}

	public Date getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(Date deadLineDate) {
		this.deadLineDate = deadLineDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
