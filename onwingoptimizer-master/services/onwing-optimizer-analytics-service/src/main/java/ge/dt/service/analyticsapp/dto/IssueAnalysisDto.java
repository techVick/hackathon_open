package ge.dt.service.analyticsapp.dto;

import java.util.Date;
import java.util.List;

public class IssueAnalysisDto {

	private long issueId;
	private int totalCount;
	private String name;
	private String description;
	private int expectedWindow;
	private String sbType;
	private Date hardDate;
	private long cycle;
	private int interval;
	private Date adDate;
	private Date deadLineDate;
	private Date issueStartDate;
	private Date issueEndDate;
	private Date currentDate;
	private long benefitDuration;
	private List<Long> potentialDefaulters;
	private List<Long> defaulters;

	private List<IssuePlanDetailDto> completed;
	private List<IssuePlanDetailDto> pending;

	public long getIssueId() {
		return issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<IssuePlanDetailDto> getCompleted() {
		return completed;
	}

	public void setCompleted(List<IssuePlanDetailDto> completed) {
		this.completed = completed;
	}

	public List<IssuePlanDetailDto> getPending() {
		return pending;
	}

	public void setPending(List<IssuePlanDetailDto> pending) {
		this.pending = pending;
	}

	public Date getIssueStartDate() {
		return issueStartDate;
	}

	public void setIssueStartDate(Date issueStartDate) {
		this.issueStartDate = issueStartDate;
	}

	public Date getIssueEndDate() {
		return issueEndDate;
	}

	public void setIssueEndDate(Date issueEndDate) {
		this.issueEndDate = issueEndDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public long getBenefitDuration() {
		return benefitDuration;
	}

	public void setBenefitDuration(long benefitDuration) {
		this.benefitDuration = benefitDuration;
	}

	public List<Long> getPotentialDefaulters() {
		return potentialDefaulters;
	}

	public void setPotentialDefaulters(List<Long> potentialDefaulters) {
		this.potentialDefaulters = potentialDefaulters;
	}

	public List<Long> getDefaulters() {
		return defaulters;
	}

	public void setDefaulters(List<Long> defaulters) {
		this.defaulters = defaulters;
	}

}
