package ge.dt.service.analyticsapp.dto;

import java.util.Date;
import java.util.List;

public class IssuePlanDetailDto {

	private int countNo;
	private long serial;
	private Date compliedOn;
	private Date dueOn;
	private Date issuePlanHardDate;
	private boolean isOnWing;
	private List<OpportunitiesDto> opportunities;

	public int getCountNo() {
		return countNo;
	}

	public void setCountNo(int countNo) {
		this.countNo = countNo;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
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

	public List<OpportunitiesDto> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<OpportunitiesDto> opportunities) {
		this.opportunities = opportunities;
	}

	public Date getIssuePlanHardDate() {
		return issuePlanHardDate;
	}

	public void setIssuePlanHardDate(Date issuePlanHardDate) {
		this.issuePlanHardDate = issuePlanHardDate;
	}

	public boolean isOnWing() {
		return isOnWing;
	}

	public void setOnWing(boolean isOnWing) {
		this.isOnWing = isOnWing;
	}

}
