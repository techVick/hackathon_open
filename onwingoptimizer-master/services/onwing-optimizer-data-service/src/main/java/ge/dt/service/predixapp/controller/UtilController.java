package ge.dt.service.predixapp.controller;

import ge.dt.service.predixapp.operation.UtilOperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/app/predix/v1/util")
public class UtilController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UtilOperations utilOperations;

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteAllRecords() {
		logger.info("Delete All Records");
		utilOperations.deleteAllRecords();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
