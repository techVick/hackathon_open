package ge.dt.service.predixapp.controller;

import ge.dt.service.predixapp.dto.AirCraftPlanDto;
import ge.dt.service.predixapp.operation.AirCraftPlanOperations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/app/predix/v1/aircraftplan")
public class AirCraftPlanController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AirCraftPlanOperations airCraftPlanOperations;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AirCraftPlanDto>> getAirCraftPlanDetails() {
		logger.info("Get AirCraftPlan details");
		List<AirCraftPlanDto> airCraftPlanDtoList = airCraftPlanOperations
				.getAirCraftPlanDetails();

		if (null != airCraftPlanDtoList && !airCraftPlanDtoList.isEmpty()) {
			return new ResponseEntity<List<AirCraftPlanDto>>(
					airCraftPlanDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<AirCraftPlanDto>>(
					HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addAirCraftPlan(
			@RequestBody List<AirCraftPlanDto> airCraftPlanDtoList) {
		logger.info("Update AirCraftPlan details");
		airCraftPlanOperations.addAirCraftPlans(airCraftPlanDtoList);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
