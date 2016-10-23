package ge.dt.service.analyticsapp.util;

import ge.dt.service.analyticsapp.dto.IssuePlanDto;

import java.util.Comparator;

public class DueDateComparator implements Comparator<IssuePlanDto> {

	@Override
	public int compare(IssuePlanDto issuePlanDetailDto1,
			IssuePlanDto issuePlanDetailDto2) {
		return issuePlanDetailDto1.getDueOn().compareTo(
				issuePlanDetailDto2.getDueOn());
	}

}
