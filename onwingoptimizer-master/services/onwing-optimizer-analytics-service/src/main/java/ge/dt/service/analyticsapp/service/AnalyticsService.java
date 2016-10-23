package ge.dt.service.analyticsapp.service;

import ge.dt.service.analyticsapp.dto.AirCraftPlanDto;
import ge.dt.service.analyticsapp.dto.EngineDto;
import ge.dt.service.analyticsapp.dto.EngineOpportunityCombo;
import ge.dt.service.analyticsapp.dto.IssueAnalysisDto;
import ge.dt.service.analyticsapp.dto.IssueDto;
import ge.dt.service.analyticsapp.dto.IssuePlanDetailDto;
import ge.dt.service.analyticsapp.dto.IssuePlanDto;
import ge.dt.service.analyticsapp.dto.IssueSummaryDto;
import ge.dt.service.analyticsapp.dto.OnWingDataDto;
import ge.dt.service.analyticsapp.dto.OnWingSummaryDto;
import ge.dt.service.analyticsapp.dto.OpportunitiesDto;
import ge.dt.service.analyticsapp.util.ComplianceDateComparator;
import ge.dt.service.analyticsapp.util.DueDateComparator;
import ge.dt.service.analyticsapp.util.OnWingAnalyticsConstantUtil;
import ge.dt.service.analyticsapp.util.OpportunityDateComparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AnalyticsService {

	public List<IssueSummaryDto> getIssueSummaryDetails(
			OnWingSummaryDto onWingSummaryDto) {

		List<IssueSummaryDto> issueSummaryDtoList = new ArrayList<>();

		for (IssueDto issueDto : onWingSummaryDto.getIssues()) {
			IssueSummaryDto issueSummaryDto = new IssueSummaryDto();
			issueSummaryDto.setIssueId(issueDto.getId());
			issueSummaryDto.setName(issueDto.getName());

			List<IssuePlanDto> issuePlanListCompleted = new ArrayList<>();
			List<IssuePlanDto> issuePlanListPending = new ArrayList<>();

			for (IssuePlanDto issuePlanDto : issueDto.getIssuePlans()) {
				if (issuePlanDto
						.getStatus()
						.equalsIgnoreCase(
								OnWingAnalyticsConstantUtil.ISSUE_PLAN_STATUS_COMPLIANCE)) {
					// populate completed data
					issuePlanListCompleted.add(issuePlanDto);
				} else {
					// populate pending data
					issuePlanListPending.add(issuePlanDto);
				}
			}

			int completed = issuePlanListCompleted.size();
			int scheduled = issuePlanListPending.size();
			int totalCount = completed + scheduled;

			issueSummaryDto.setCompleted(completed);
			issueSummaryDto.setScheduled(scheduled);
			issueSummaryDto.setTotalCount(totalCount);

			// to do
			issueSummaryDto.setIssueStartDate(issueDto.getHardDate());
			issueSummaryDto.setIssueEndDate(getIssueEndDate(
					issueDto.getDeadLineDate(), issueDto.getIssuePlans()));
			issueSummaryDto.setCurrentDate(new Date());

			issueSummaryDtoList.add(issueSummaryDto);
		}

		return issueSummaryDtoList;
	}

	public IssueAnalysisDto getIssueAnalysisDetails(
			OnWingDataDto onWingDataDto, boolean etops) {

		// Get Completed , Offwing and Pending List
		List<IssuePlanDto> issuePlanDtoCompletedList = new ArrayList<>();
		// List<IssuePlanDto> issuePlanDtoOffwingList = new ArrayList<>();
		List<IssuePlanDto> issuePlanDtoPendingList = new ArrayList<>();

		List<IssuePlanDto> issuePlanDtoList = onWingDataDto.getIssue()
				.getIssuePlans();

		for (IssuePlanDto issuePlanDto : issuePlanDtoList) {

			// find if on wing or offwing

			if (issuePlanDto.getStatus().equalsIgnoreCase(
					OnWingAnalyticsConstantUtil.ISSUE_PLAN_STATUS_COMPLIANCE)) {
				// populate completed data
				issuePlanDtoCompletedList.add(issuePlanDto);
			} else {
				issuePlanDtoPendingList.add(issuePlanDto);

				// if (isOnWing(issuePlanDto.getSerial(),
				// onWingDataDto.getEngines())) {
				// // if onwing
				// // populate pending data
				// issuePlanDtoPendingList.add(issuePlanDto);
				// } else {
				// // populate offwing data
				// issuePlanDtoOffwingList.add(issuePlanDto);
				// }

			}

		}

		IssueAnalysisDto issueAnalysisDto = populateIssuePlanData(
				onWingDataDto, issuePlanDtoCompletedList,
				issuePlanDtoPendingList, etops);

		return issueAnalysisDto;
	}

	private IssueAnalysisDto populateIssuePlanData(OnWingDataDto onWingDataDto,
			List<IssuePlanDto> issuePlanDtoCompletedList,
			List<IssuePlanDto> issuePlanDtoPendingList, boolean etops) {

		IssueAnalysisDto issueAnalysisDto = populateIssueData(onWingDataDto);
		int totalCount = onWingDataDto.getIssue().getIssuePlans().size();

		// int i = 1;
		List<IssuePlanDetailDto> completed = new ArrayList<>();

		// sort issuePlanDtoCompletedList
		Collections.sort(issuePlanDtoCompletedList,
				new ComplianceDateComparator());
		// populate completed data
		for (IssuePlanDto issuePlanDto : issuePlanDtoCompletedList) {
			IssuePlanDetailDto completedIssuePlanDetailDto = new IssuePlanDetailDto();
			completedIssuePlanDetailDto.setCountNo(totalCount);
			completedIssuePlanDetailDto.setSerial(issuePlanDto.getSerial());
			completedIssuePlanDetailDto.setCompliedOn(issuePlanDto
					.getCompliedOn());
			completedIssuePlanDetailDto.setDueOn(issuePlanDto.getDueOn());

			completed.add(completedIssuePlanDetailDto);
			totalCount--;
		}
		issueAnalysisDto.setCompleted(completed);

		// List<IssuePlanDetailDto> offWing = new ArrayList<>();
		// // sort issuePlanDtoOffwingList - to do
		// Collections.sort(issuePlanDtoOffwingList, new DueDateComparator());
		// // populate offwing data
		// for (IssuePlanDto issuePlanDto : issuePlanDtoOffwingList) {
		// IssuePlanDetailDto offWingIssuePlanDetailDto = new
		// IssuePlanDetailDto();
		// offWingIssuePlanDetailDto.setCountNo(totalCount);
		// offWingIssuePlanDetailDto.setSerial(issuePlanDto.getSerial());
		// offWingIssuePlanDetailDto.setCompliedOn(issuePlanDto
		// .getCompliedOn());
		// offWingIssuePlanDetailDto.setDueOn(issuePlanDto.getDueOn());
		// offWing.add(offWingIssuePlanDetailDto);
		// totalCount--;
		// }
		// issueAnalysisDto.setOffWing(offWing);

		List<IssuePlanDetailDto> pending = new ArrayList<>();
		// sort issuePlanDtoPendingList - to do
		Collections.sort(issuePlanDtoPendingList, new DueDateComparator());
		List<OpportunitiesDto> allOpportunities = new ArrayList<>();
		// populate pending data
		for (IssuePlanDto issuePlanDto : issuePlanDtoPendingList) {
			IssuePlanDetailDto pendingIssuePlanDetailDto = new IssuePlanDetailDto();
			pendingIssuePlanDetailDto.setCountNo(totalCount);
			pendingIssuePlanDetailDto.setSerial(issuePlanDto.getSerial());
			pendingIssuePlanDetailDto.setCompliedOn(issuePlanDto
					.getCompliedOn());
			pendingIssuePlanDetailDto.setDueOn(issuePlanDto.getDueOn());
			pendingIssuePlanDetailDto.setOnWing(isOnWing(
					issuePlanDto.getSerial(), onWingDataDto.getEngines()));

			// set Opportunities

			pendingIssuePlanDetailDto
					.setOpportunities(getOpportunitiesForEngine(issuePlanDto
							.getSerial(), onWingDataDto.getIssue()
							.getExpectedWindow(), onWingDataDto.getEngines(),
							onWingDataDto.getAirCraftPlans(), issuePlanDto
									.getDueOn()));
			List<OpportunitiesDto> opportunitiesDtoList = pendingIssuePlanDetailDto
					.getOpportunities();
			if (!opportunitiesDtoList.isEmpty()) {
				allOpportunities.addAll(opportunitiesDtoList);
			}

			// set issue plan hard date if cycle is > 0
			Date issuePlanHardDate = null;
			long cycleForIssue = onWingDataDto.getIssue().getCycle();
			if (cycleForIssue > 0) {
				issuePlanHardDate = getIssuePlanHardDate(cycleForIssue,
						onWingDataDto, issuePlanDto);

			}
			pendingIssuePlanDetailDto.setIssuePlanHardDate(issuePlanHardDate);

			pending.add(pendingIssuePlanDetailDto);
			totalCount--;
		}

		// eTops case
		// Get all Engines
		// Group them by Aircraft

		if (etops) {

			Map<String, List<IssuePlanDetailDto>> aircraftEngineMap = new HashMap<String, List<IssuePlanDetailDto>>();

			for (IssuePlanDetailDto planDto : pending) {

				String aircraftSerial = onWingDataDto
						.getEngines()
						.stream()
						.filter(engine -> engine.getSerial() == planDto
								.getSerial()).findFirst().get().getTopSerial();

				if (aircraftEngineMap.containsKey(aircraftSerial)) {
					aircraftEngineMap.get(aircraftSerial).add(planDto);
				} else {
					List<IssuePlanDetailDto> list = new ArrayList<IssuePlanDetailDto>();
					list.add(planDto);
					aircraftEngineMap.put(aircraftSerial, list);
				}

			}

			for (String aircraftSerial : aircraftEngineMap.keySet()) {

				List<IssuePlanDetailDto> plans = aircraftEngineMap
						.get(aircraftSerial);

				Map<Date, List<EngineOpportunityCombo>> dataBasedMap = new HashMap<>();

				for (IssuePlanDetailDto planDto : plans) {

					for (OpportunitiesDto oppDto : planDto.getOpportunities()) {

						if (dataBasedMap.containsKey(oppDto
								.getPlannedStartDate())) {
							dataBasedMap.get(oppDto.getPlannedStartDate())
									.add(new EngineOpportunityCombo(planDto,
											oppDto));
						} else {
							List<EngineOpportunityCombo> list = new ArrayList<>();
							list.add(new EngineOpportunityCombo(planDto, oppDto));
							dataBasedMap
									.put(oppDto.getPlannedStartDate(), list);
						}
					}
				}

				// Sort the HashMap by date

				// Iterate through the date and Mark items for removal

				boolean switchRemoval = false;

				for (Date dt : dataBasedMap.keySet()) {

					int count = dataBasedMap.get(dt).size();

					if (!switchRemoval) {

						if (count > 3) {

							count--;

							dataBasedMap
									.get(dt)
									.get(3)
									.getIssuePlan()
									.getOpportunities()
									.remove(dataBasedMap.get(dt).get(3)
											.getOpporunity());
						}

						if (count > 2) {
							dataBasedMap
									.get(dt)
									.get(2)
									.getIssuePlan()
									.getOpportunities()
									.remove(dataBasedMap.get(dt).get(2)
											.getOpporunity());
						}

						switchRemoval = true;
					} else {

						if (count > 3) {
							dataBasedMap
									.get(dt)
									.get(0)
									.getIssuePlan()
									.getOpportunities()
									.remove(dataBasedMap.get(dt).get(0)
											.getOpporunity());
							count--;
						}

						if (count > 2) {
							dataBasedMap
									.get(dt)
									.get(1)
									.getIssuePlan()
									.getOpportunities()
									.remove(dataBasedMap.get(dt).get(1)
											.getOpporunity());
						}

						switchRemoval = false;
					}
				}

			}
		}

		// Benefit Duration
		OpportunitiesDto minOpportunityDate = Collections.max(allOpportunities,
				new OpportunityDateComparator());
		IssuePlanDto issuePlanMaxDueOn = Collections.max(
				issuePlanDtoPendingList, new DueDateComparator());

		long diff = issuePlanMaxDueOn.getDueOn().getTime()
				- minOpportunityDate.getPlannedStartDate().getTime();
		issueAnalysisDto.setBenefitDuration(TimeUnit.DAYS.convert(diff,
				TimeUnit.MILLISECONDS));

		// potential defaulter
		List<IssuePlanDto> potentialDefaulters = issuePlanDtoPendingList
				.stream()
				.filter((x) -> x.getDueOn().getTime() > issueAnalysisDto
						.getDeadLineDate().getTime())
				.collect(Collectors.toList());

		List<Long> serialNumbers = new ArrayList<Long>();
		if (null != potentialDefaulters && !potentialDefaulters.isEmpty()) {
			for (IssuePlanDto potentialDefaulter : potentialDefaulters) {
				serialNumbers.add(potentialDefaulter.getSerial());
			}
		}
		issueAnalysisDto.setPotentialDefaulters(serialNumbers);

		// defaulters
		// Get Onwing Due
		// Compare with today's date
		List<IssuePlanDetailDto> onWingPlans = pending.stream()
				.filter((x) -> x.isOnWing()).collect(Collectors.toList());

		List<Long> serialNumbersDefaults = new ArrayList<Long>();
		if (!onWingPlans.isEmpty()) {

			List<IssuePlanDetailDto> defaulters = pending
					.stream()
					.filter((x) -> x.getDueOn().getTime() < new Date()
							.getTime()).collect(Collectors.toList());

			if (null != defaulters && !defaulters.isEmpty()) {
				for (IssuePlanDetailDto defaulter : defaulters) {
					serialNumbersDefaults.add(defaulter.getSerial());
				}
			}

		}
		issueAnalysisDto.setDefaulters(serialNumbersDefaults);

		issueAnalysisDto.setPending(pending);

		return issueAnalysisDto;
	}

	private Date getIssuePlanHardDate(long cycleForIssue,
			OnWingDataDto onWingDataDto, IssuePlanDto issuePlanDto) {
		Date issuePlanHardDate = null;
		EngineDto engineForSerial = null;
		for (EngineDto engineDto : onWingDataDto.getEngines()) {
			if (engineDto.getSerial() == issuePlanDto.getSerial()
					&& engineDto
							.getInvStatus()
							.equalsIgnoreCase(
									OnWingAnalyticsConstantUtil.ENGINE_INV_STATUS_INSTALLED)) {
				engineForSerial = engineDto;
			}
		}

		if (null != engineForSerial) {
			long cycleForEngine = engineForSerial.getCycle();
			long cyclePending = cycleForIssue - cycleForEngine;
			long noOfDaysPending = cyclePending
					/ OnWingAnalyticsConstantUtil.NUMBER_OF_CYCLES_PER_DAY;
			System.out.println("No of days pending:" + noOfDaysPending);
			Date currentDate = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, (int) noOfDaysPending); // add days
			issuePlanHardDate = cal.getTime();
		}
		return issuePlanHardDate;
	}

	private IssueAnalysisDto populateIssueData(OnWingDataDto onWingDataDto) {
		IssueAnalysisDto issueAnalysisDto = new IssueAnalysisDto();

		// populate initial data
		int totalCount = onWingDataDto.getIssue().getIssuePlans().size();
		issueAnalysisDto.setTotalCount(totalCount);
		issueAnalysisDto.setIssueId(onWingDataDto.getIssue().getId());
		issueAnalysisDto.setName(onWingDataDto.getIssue().getName());
		issueAnalysisDto.setDescription(onWingDataDto.getIssue()
				.getDescription());
		issueAnalysisDto.setExpectedWindow(onWingDataDto.getIssue()
				.getExpectedWindow());
		issueAnalysisDto.setSbType(onWingDataDto.getIssue().getSbType());

		issueAnalysisDto.setHardDate(onWingDataDto.getIssue().getHardDate());
		issueAnalysisDto.setCycle(onWingDataDto.getIssue().getCycle());
		issueAnalysisDto.setInterval(onWingDataDto.getIssue().getInterval());
		issueAnalysisDto.setAdDate(onWingDataDto.getIssue().getAdDate());
		issueAnalysisDto.setDeadLineDate(onWingDataDto.getIssue()
				.getDeadLineDate());

		// to plot graph - reduce 3 months from start date
		issueAnalysisDto.setIssueStartDate(addMonthToDate(onWingDataDto
				.getIssue().getHardDate(), -3));
		Date issueEndDate = getIssueEndDate(onWingDataDto.getIssue()
				.getDeadLineDate(), onWingDataDto.getIssue().getIssuePlans());

		// to plot graph - add 3 months from start date
		issueAnalysisDto.setIssueEndDate(addMonthToDate(issueEndDate, 3));
		issueAnalysisDto.setCurrentDate(new Date());
		return issueAnalysisDto;
	}

	private boolean isOnWing(long engineSerial, List<EngineDto> engineDtoList) {
		boolean isOnWing = Boolean.FALSE;

		for (EngineDto engineDto : engineDtoList) {
			if (engineDto.getSerial() == engineSerial
					&& engineDto
							.getInvStatus()
							.equalsIgnoreCase(
									OnWingAnalyticsConstantUtil.ENGINE_INV_STATUS_INSTALLED)) {
				return Boolean.TRUE;
			}
		}

		return isOnWing;
	}

	private List<OpportunitiesDto> getOpportunitiesForEngine(long engineSerial,
			int expectedWindow, List<EngineDto> engineDtoList,
			List<AirCraftPlanDto> airCraftPlanDtoList, Date dueOn) {
		List<OpportunitiesDto> opportunitiesListDto = new ArrayList<>();
		List<AirCraftPlanDto> airCraftPlanDtoShortListed = new ArrayList<>();

		EngineDto engineForSerial = null;

		for (EngineDto engineDto : engineDtoList) {
			if (engineDto.getSerial() == engineSerial
					&& engineDto
							.getInvStatus()
							.equalsIgnoreCase(
									OnWingAnalyticsConstantUtil.ENGINE_INV_STATUS_INSTALLED)) {
				engineForSerial = engineDto;
			}
		}

		if (null != engineForSerial) {
			for (AirCraftPlanDto airCraftPlanDto : airCraftPlanDtoList) {
				if (engineForSerial.getTopSerial().equalsIgnoreCase(
						airCraftPlanDto.getAssetSerial())
						&& airCraftPlanDto
								.getPackageType()
								.equalsIgnoreCase(
										OnWingAnalyticsConstantUtil.AIRCRAFT_PACKAGE_TYPE_WING)
						&& airCraftPlanDto.getDuration() >= expectedWindow
						&& airCraftPlanDto.getPlannedStartDate().before(dueOn)
						&& airCraftPlanDto.getPlannedStartDate().after(
								new Date())) {

					airCraftPlanDtoShortListed.add(airCraftPlanDto);

				}

			}
		}

		// sort airCraftPlanDtoShortListed
		for (AirCraftPlanDto airCraftPlanDto : airCraftPlanDtoShortListed) {
			OpportunitiesDto opportunitiesDto = new OpportunitiesDto();
			opportunitiesDto.setAssetSerial(airCraftPlanDto.getAssetSerial());
			opportunitiesDto.setPackageType(airCraftPlanDto.getPackageType());
			opportunitiesDto.setPlannedStartDate(airCraftPlanDto
					.getPlannedStartDate());
			opportunitiesDto.setPlannedEndDate(airCraftPlanDto
					.getPlannedEndDate());
			opportunitiesDto.setDuration(airCraftPlanDto.getDuration());
			opportunitiesListDto.add(opportunitiesDto);

		}

		return opportunitiesListDto;
	}

	private Date getIssueEndDate(Date deadLineDate,
			List<IssuePlanDto> issuePlanDtoList) {
		Date issueEndDate = null;
		// get Max of issueplandto - due date
		Collections.sort(issuePlanDtoList, new DueDateComparator());
		Date dueDateMax = issuePlanDtoList.get(issuePlanDtoList.size() - 1)
				.getDueOn();

		if (null != deadLineDate) {

			if (deadLineDate.after(dueDateMax)) {
				issueEndDate = deadLineDate;
			} else {
				issueEndDate = dueDateMax;
			}

		} else {
			issueEndDate = dueDateMax;
		}

		return issueEndDate;
	}

	private Date addMonthToDate(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
}
