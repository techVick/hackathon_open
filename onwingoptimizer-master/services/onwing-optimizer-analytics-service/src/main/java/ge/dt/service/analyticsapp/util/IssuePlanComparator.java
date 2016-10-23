package ge.dt.service.analyticsapp.util;

import ge.dt.service.analyticsapp.model.IssuePlan;

import java.util.Comparator;

public class IssuePlanComparator implements Comparator<IssuePlan> {

	@Override
	public int compare(IssuePlan issuePlanDetailDto1,
			IssuePlan issuePlanDetailDto2) {
		Long serialValue1 = Long.valueOf(issuePlanDetailDto1.getSerial());
		Long serialValue2 = Long.valueOf(issuePlanDetailDto2.getSerial());
		int serialComp = serialValue1.compareTo(serialValue2);

		if (serialComp != 0) {
			return serialComp;
		} else {
			return issuePlanDetailDto2.getCreatedDate().compareTo(
					issuePlanDetailDto1.getCreatedDate());
		}

	}

}
