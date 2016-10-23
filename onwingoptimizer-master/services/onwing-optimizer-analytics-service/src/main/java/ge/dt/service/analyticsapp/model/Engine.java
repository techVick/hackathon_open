package ge.dt.service.analyticsapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "engineSeq", initialValue = 101, allocationSize = 1)
public class Engine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -973921706449576794L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engineSeq")
	private long id;

	private long serial;

	private String part;

	private String invStatus;

	private String topSerial;

	private int installedOn;

	private long cycle;

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

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

	public String getTopSerial() {
		return topSerial;
	}

	public void setTopSerial(String topSerial) {
		this.topSerial = topSerial;
	}

	public int getInstalledOn() {
		return installedOn;
	}

	public void setInstalledOn(int installedOn) {
		this.installedOn = installedOn;
	}

	public long getCycle() {
		return cycle;
	}

	public void setCycle(long cycle) {
		this.cycle = cycle;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
