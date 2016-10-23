package ge.dt.service.analyticsapp.controller;

import ge.dt.service.analyticsapp.dto.IssueAnalysisDto;
import ge.dt.service.analyticsapp.dto.IssueDropDownDto;
import ge.dt.service.analyticsapp.dto.IssueSummaryDto;
import ge.dt.service.analyticsapp.operation.WidgetOperations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onwing")
public class WidgetController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WidgetOperations widgetOperations;

	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public ResponseEntity<List<IssueDropDownDto>> getIssueListForDropDown() {

		logger.info("Get Issue List");
		List<IssueDropDownDto> issueDropDownDtoList = widgetOperations
				.getIssueListForDropDown();

		if (null != issueDropDownDtoList && !issueDropDownDtoList.isEmpty()) {
			return new ResponseEntity<List<IssueDropDownDto>>(
					issueDropDownDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<IssueDropDownDto>>(
					HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(value = "/issuesummary", method = RequestMethod.GET)
	public ResponseEntity<List<IssueSummaryDto>> getIssueSummary() {

		List<IssueSummaryDto> issueSummarDtoList = widgetOperations
				.getIssueSummaryDetails();

		if (null != issueSummarDtoList) {
			return new ResponseEntity<List<IssueSummaryDto>>(
					issueSummarDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<IssueSummaryDto>>(
					HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/issueanalysis/{issueid}", method = RequestMethod.GET)
	public ResponseEntity<IssueAnalysisDto> getIssueAnalysisDetails(
			@PathVariable Long issueid,
			@RequestParam(value = "etops", required = false) boolean etops) {
		System.out.println("Etops Value:" + etops);

		IssueAnalysisDto issueAnalysisDto = widgetOperations
				.getIssueAnalysisDetails(issueid, etops);

		if (null != issueAnalysisDto) {
			return new ResponseEntity<IssueAnalysisDto>(issueAnalysisDto,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<IssueAnalysisDto>(HttpStatus.NO_CONTENT);
		}
	}
}
