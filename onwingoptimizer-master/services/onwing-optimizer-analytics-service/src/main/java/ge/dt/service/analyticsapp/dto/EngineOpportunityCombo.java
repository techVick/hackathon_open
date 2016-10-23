package ge.dt.service.analyticsapp.dto;

public class EngineOpportunityCombo {

	private IssuePlanDetailDto issuePlan;
	private OpportunitiesDto opporunity;

	public EngineOpportunityCombo(IssuePlanDetailDto e, OpportunitiesDto o) {
		issuePlan = e;
		opporunity = o;
	}

	public OpportunitiesDto getOpporunity() {
		return opporunity;
	}

	public void setOpporunity(OpportunitiesDto opporunity) {
		this.opporunity = opporunity;
	}

	public IssuePlanDetailDto getIssuePlan() {
		return issuePlan;
	}

	public void setIssuePlan(IssuePlanDetailDto issuePlan) {
		this.issuePlan = issuePlan;
	}

}
