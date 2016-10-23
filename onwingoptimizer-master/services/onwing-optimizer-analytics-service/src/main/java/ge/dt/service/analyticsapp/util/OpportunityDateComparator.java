package ge.dt.service.analyticsapp.util;

import ge.dt.service.analyticsapp.dto.OpportunitiesDto;

import java.util.Comparator;

public class OpportunityDateComparator implements Comparator<OpportunitiesDto> {

	@Override
	public int compare(OpportunitiesDto opportunitiesDto1,
			OpportunitiesDto opportunitiesDto2) {
		return opportunitiesDto1.getPlannedStartDate().compareTo(
				opportunitiesDto2.getPlannedStartDate());
	}

}
