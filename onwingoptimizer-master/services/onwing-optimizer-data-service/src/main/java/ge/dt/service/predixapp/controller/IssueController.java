package ge.dt.service.predixapp.controller;

import ge.dt.service.predixapp.dto.IssueDto;
import ge.dt.service.predixapp.operation.IssueOperations;

import java.text.ParseException;
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
@RequestMapping("api/app/predix/v1/issue")
public class IssueController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IssueOperations issueOperations;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<IssueDto>> getIssueDetails() {
		logger.info("Get Issue details");
		List<IssueDto> issueDtoList = issueOperations.getIssueDetails();

		if (null != issueDtoList && !issueDtoList.isEmpty()) {
			return new ResponseEntity<List<IssueDto>>(issueDtoList,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<IssueDto>>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addIssueDetails(
			@RequestBody List<IssueDto> issueDtoList) {
		logger.info("Update Issue details");
		try {
			issueOperations.addIssues(issueDtoList);
		} catch (ParseException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
