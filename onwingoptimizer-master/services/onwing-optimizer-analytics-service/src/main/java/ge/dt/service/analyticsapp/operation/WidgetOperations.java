package ge.dt.service.analyticsapp.operation;

import ge.dt.service.analyticsapp.dto.AirCraftPlanDto;
import ge.dt.service.analyticsapp.dto.EngineDto;
import ge.dt.service.analyticsapp.dto.IssueAnalysisDto;
import ge.dt.service.analyticsapp.dto.IssueDropDownDto;
import ge.dt.service.analyticsapp.dto.IssueDto;
import ge.dt.service.analyticsapp.dto.IssuePlanDto;
import ge.dt.service.analyticsapp.dto.IssueSummaryDto;
import ge.dt.service.analyticsapp.dto.OnWingDataDto;
import ge.dt.service.analyticsapp.dto.OnWingSummaryDto;
import ge.dt.service.analyticsapp.model.AirCraftPlan;
import ge.dt.service.analyticsapp.model.Engine;
import ge.dt.service.analyticsapp.model.Issue;
import ge.dt.service.analyticsapp.model.IssuePlan;
import ge.dt.service.analyticsapp.repository.AirCraftPlanRepository;
import ge.dt.service.analyticsapp.repository.EngineRepository;
import ge.dt.service.analyticsapp.repository.IssueRepository;
import ge.dt.service.analyticsapp.service.AnalyticsService;
import ge.dt.service.analyticsapp.util.IssuePlanComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WidgetOperations {

	@Autowired
	private AirCraftPlanRepository airCraftPlanRepository;

	@Autowired
	private EngineRepository engineRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private AnalyticsService analyticsService;

	public List<IssueDropDownDto> getIssueListForDropDown() {
		List<Issue> issueList = (List<Issue>) issueRepository.findAll();
		List<IssueDropDownDto> issueDropDownDtoList = new ArrayList<>();

		for (Issue issue : issueList) {
			IssueDropDownDto issueDropDownDto = new IssueDropDownDto();
			issueDropDownDto.setId(issue.getId());
			issueDropDownDto.setName(issue.getName());
			issueDropDownDto.setDescription(issue.getDescription());
			issueDropDownDto.setSbType(issue.getSbType());
			issueDropDownDtoList.add(issueDropDownDto);
		}
		return issueDropDownDtoList;
	}

	public List<IssueSummaryDto> getIssueSummaryDetails() {
		OnWingSummaryDto onWingSummaryDto = new OnWingSummaryDto();

		List<Issue> issueList = (List<Issue>) issueRepository.findAll();
		List<IssueDto> issueDtoList = new ArrayList<>();

		for (Issue issue : issueList) {
			IssueDto issueDto = new IssueDto();
			BeanUtils.copyProperties(issue, issueDto);
			List<IssuePlanDto> issuePlanDtoList = new ArrayList<>();
			List<IssuePlan> issuePlanListFromDb = issue.getIssuePlans();

			// sort the collection
			Collections.sort(issuePlanListFromDb, new IssuePlanComparator());

			for (IssuePlan issuePlan : issuePlanListFromDb) {
				IssuePlanDto issuePlanDto = new IssuePlanDto();
				BeanUtils.copyProperties(issuePlan, issuePlanDto);
				issuePlanDto.setIssue(issueDto);
				if (!issuePlanDtoList.contains(issuePlanDto)) {
					issuePlanDtoList.add(issuePlanDto);
				}

			}
			issueDto.setIssuePlans(issuePlanDtoList);
			issueDtoList.add(issueDto);

		}

		onWingSummaryDto.setIssues(issueDtoList);

		return analyticsService.getIssueSummaryDetails(onWingSummaryDto);

	}

	public IssueAnalysisDto getIssueAnalysisDetails(Long issueId, boolean etops) {
		IssueAnalysisDto issueAnalysisDto = null;
		OnWingDataDto onWingDataDto = getOnWingData(issueId);
		if (null != onWingDataDto) {
			issueAnalysisDto = analyticsService
					.getIssueAnalysisDetails(onWingDataDto, etops);
		}
		return issueAnalysisDto;
	}

	private OnWingDataDto getOnWingData(Long issueId) {

		OnWingDataDto onWingDataDto = null;
		// Get Issue details for IssueId
		Issue issue = issueRepository.findOne(issueId);

		if (null != issue) {

			IssueDto issueDto = new IssueDto();
			BeanUtils.copyProperties(issue, issueDto);

			List<EngineDto> engineDtoList = new ArrayList<>();
			List<AirCraftPlanDto> airCraftPlanDtoList = new ArrayList<>();
			List<IssuePlanDto> issuePlanDtoList = new ArrayList<>();

			List<IssuePlan> issuePlanListFromDb = issue.getIssuePlans();
			// sort the collection
			Collections.sort(issuePlanListFromDb, new IssuePlanComparator());

			for (IssuePlan issuePlan : issue.getIssuePlans()) {
				IssuePlanDto issuePlanDto = new IssuePlanDto();
				BeanUtils.copyProperties(issuePlan, issuePlanDto);
				issuePlanDto.setIssue(issueDto);
				if (!issuePlanDtoList.contains(issuePlanDto)) {
					issuePlanDtoList.add(issuePlanDto);
				}

				// Get Engine Details For Issue
				Engine engine = engineRepository.findBySerial(issuePlan
						.getSerial());
				EngineDto engineDto = new EngineDto();
				BeanUtils.copyProperties(engine, engineDto);

				if (!engineDtoList.contains(engineDto)) {
					engineDtoList.add(engineDto);
				}

				// Get AirCraftPlan Details for Issue
				List<AirCraftPlan> airCraftPlanList = airCraftPlanRepository
						.findByAssetSerial(engine.getTopSerial());

				for (AirCraftPlan airCraftPlan : airCraftPlanList) {
					AirCraftPlanDto airCraftPlanDto = new AirCraftPlanDto();
					BeanUtils.copyProperties(airCraftPlan, airCraftPlanDto);

					if (!airCraftPlanDtoList.contains(airCraftPlanDto)) {
						airCraftPlanDtoList.add(airCraftPlanDto);
					}
				}

			}
			issueDto.setIssuePlans(issuePlanDtoList);

			// Populate OnwingDataDto
			onWingDataDto = new OnWingDataDto();
			onWingDataDto.setIssue(issueDto);
			onWingDataDto.setEngines(engineDtoList);
			onWingDataDto.setAirCraftPlans(airCraftPlanDtoList);
		}

		return onWingDataDto;
	}

}
