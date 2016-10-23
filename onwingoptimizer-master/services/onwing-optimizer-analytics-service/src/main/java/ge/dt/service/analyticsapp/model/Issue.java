package ge.dt.service.analyticsapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "issueSeq", initialValue = 101, allocationSize = 1)
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2557759687942545923L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issueSeq")
	private long id;

	private String name;

	private String description;

	private int expectedWindow;

	private String sbType;

	private Date hardDate;

	private long cycle;

	private int interval;

	private Date adDate;

	private Date deadLineDate;

	private Date createdDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issue", cascade = CascadeType.ALL)
	private List<IssuePlan> issuePlans;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IssuePlan> getIssuePlans() {
		return issuePlans;
	}

	public void setIssuePlans(List<IssuePlan> issuePlans) {
		this.issuePlans = issuePlans;
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
