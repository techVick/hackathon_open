package ge.dt.service.analyticsapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "airCraftPlanSeq", initialValue = 101, allocationSize = 1)
public class AirCraftPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -222125305060067843L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airCraftPlanSeq")
	private long id;

	private String assetSerial;

	private String packageType;

	private Date plannedStartDate;

	private Date plannedEndDate;

	private int duration;

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
