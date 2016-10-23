package ge.dt.service.predixapp.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ge.dt.service.predixapp.dto.AirCraftPlanDto;
import ge.dt.service.predixapp.model.AirCraftPlan;
import ge.dt.service.predixapp.repository.AirCraftPlanRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirCraftPlanOperations {

	@Autowired
	private AirCraftPlanRepository airCraftPlanRepository;

	public List<AirCraftPlanDto> getAirCraftPlanDetails() {
		List<AirCraftPlan> airCraftPlanList = (List<AirCraftPlan>) airCraftPlanRepository
				.findAll();
		List<AirCraftPlanDto> airCraftPlanDtoList = new ArrayList<>();

		for (AirCraftPlan airCraftPlan : airCraftPlanList) {
			AirCraftPlanDto airCraftPlanDto = new AirCraftPlanDto();
			BeanUtils.copyProperties(airCraftPlan, airCraftPlanDto);
			airCraftPlanDtoList.add(airCraftPlanDto);
		}
		return airCraftPlanDtoList;
	}

	public void addAirCraftPlans(List<AirCraftPlanDto> airCraftPlanDtoList) {
		List<AirCraftPlan> airCraftPlanList = new ArrayList<>();
		for (AirCraftPlanDto airCraftPlanDto : airCraftPlanDtoList) {
			AirCraftPlan airCraftPlan = new AirCraftPlan();
			BeanUtils.copyProperties(airCraftPlanDto, airCraftPlan);
			airCraftPlan.setCreatedDate(new Date());
			airCraftPlanList.add(airCraftPlan);
		}

		airCraftPlanRepository.save(airCraftPlanList);
	}

	// public void addAirCraftPlans(List<AirCraftPlanDto> airCraftPlanDtoList) {
	// List<AirCraftPlan> airCraftPlanList = new ArrayList<>();
	// for (AirCraftPlanDto airCraftPlanDto : airCraftPlanDtoList) {
	// AirCraftPlan airCraftPlan = null;
	// List<AirCraftPlan> airCraftPlanDtoFromDb = airCraftPlanRepository
	// .findByAssetSerial(airCraftPlanDto.getAssetSerial());
	// if (null != airCraftPlanDtoFromDb
	// && !airCraftPlanDtoFromDb.isEmpty()) {
	// airCraftPlan = airCraftPlanDtoFromDb.get(0);
	// airCraftPlan.setPackageType(airCraftPlanDto.getPackageType());
	// airCraftPlan.setPlannedStartDate(airCraftPlanDto
	// .getPlannedStartDate());
	// airCraftPlan.setPlannedEndDate(airCraftPlanDto
	// .getPlannedEndDate());
	// airCraftPlan.setDuration(airCraftPlanDto.getDuration());
	//
	// } else {
	// airCraftPlan = new AirCraftPlan();
	// BeanUtils.copyProperties(airCraftPlanDto, airCraftPlan);
	// }
	// airCraftPlan.setCreatedDate(new Date());
	// airCraftPlanList.add(airCraftPlan);
	// }
	//
	// airCraftPlanRepository.save(airCraftPlanList);
	// }

}
