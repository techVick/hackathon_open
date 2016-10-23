package ge.dt.service.analyticsapp.util;

import ge.dt.service.analyticsapp.dto.IssuePlanDto;

import java.util.Comparator;

public class ComplianceDateComparator implements Comparator<IssuePlanDto> {

	@Override
	public int compare(IssuePlanDto issuePlanDetailDto1,
			IssuePlanDto issuePlanDetailDto2) {
		return issuePlanDetailDto1.getCompliedOn().compareTo(
				issuePlanDetailDto2.getCompliedOn());
	}

}
