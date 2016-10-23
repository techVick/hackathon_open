package ge.dt.service.analyticsapp.dto;

import java.util.List;

public class OnWingDataDto {

	private IssueDto issue;
	private List<AirCraftPlanDto> airCraftPlans;
	private List<EngineDto> engines;

	public IssueDto getIssue() {
		return issue;
	}

	public void setIssue(IssueDto issue) {
		this.issue = issue;
	}

	public List<AirCraftPlanDto> getAirCraftPlans() {
		return airCraftPlans;
	}

	public void setAirCraftPlans(List<AirCraftPlanDto> airCraftPlans) {
		this.airCraftPlans = airCraftPlans;
	}

	public List<EngineDto> getEngines() {
		return engines;
	}

	public void setEngines(List<EngineDto> engines) {
		this.engines = engines;
	}

}
