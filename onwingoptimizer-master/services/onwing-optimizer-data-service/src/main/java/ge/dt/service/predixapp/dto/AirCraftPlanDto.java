package ge.dt.service.predixapp.dto;

import ge.dt.service.predixapp.json.JsonDateDeSerializer;
import ge.dt.service.predixapp.json.JsonDateSerializer;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AirCraftPlanDto {

	private long id;

	private String assetSerial;

	private String packageType;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date plannedStartDate;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date plannedEndDate;

	private int duration;

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeSerializer.class)
	private Date createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
