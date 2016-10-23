package ge.dt.service.predixapp.controller;

import ge.dt.service.predixapp.dto.EngineDto;
import ge.dt.service.predixapp.model.Engine;
import ge.dt.service.predixapp.operation.EngineOperations;

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
@RequestMapping("api/app/predix/v1/engine")
public class EngineController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EngineOperations engineOperations;

	@RequestMapping(method = RequestMethsponseEntity<List<Engine>> getEngineDetails() {
		logger.info("Get Engine details");
		List<Engine> enginesList = engineOperations.getEngineDetails();

		if (null != enginesList && !enginesList.isEmpty()) {
			return new ResponseEntity<List<Engine>>(enginesList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Engine>>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addEngineDetails(
			@RequestBody List<EngineDto> engineDtoList) {
		logger.info("Update Engine details");
		engineOperations.addEngines(engineDtoList);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
