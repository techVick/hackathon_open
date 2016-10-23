package ge.dt.service.analyticsapp.dto;

import java.util.Date;

public class IssueSummaryDto {

	private long issueId;
	private String name;
	private int totalCount;
	private int completed;
	private int scheduled;
	private Date issueStartDate;
	private Date issueEndDate;
	private Date currentDate;

	public long getIssueId() {
		return issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getScheduled() {
		return scheduled;
	}

	public void setScheduled(int scheduled) {
		this.scheduled = scheduled;
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

}
