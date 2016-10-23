package ge.dt.service.analyticsapp.dto;

import java.util.Date;

public class OpportunitiesDto {

	private String assetSerial;

	private String packageType;

	private Date plannedStartDate;

	private Date plannedEndDate;

	private int duration;

	public String getAssetSerial() {
		return assetSerial;
	}

	public void setAssetSerial(String assetSerial) {
		this.assetSerial = assetSerial;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public Date getPlannedStartDate() {
		return plannedStartDate;
	}

	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}

	public Date getPlannedEndDate() {
		return plannedEndDate;
	}

	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
