package ge.dt.service.predixapp.operation;

import ge.dt.service.predixapp.dto.IssueDto;
import ge.dt.service.predixapp.dto.IssuePlanDto;
import ge.dt.service.predixapp.model.Issue;
import ge.dt.service.predixapp.model.IssuePlan;
import ge.dt.service.predixapp.repository.IssuePlanRepository;
import ge.dt.service.predixapp.repository.IssueRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueOperations {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private IssuePlanRepository issuePlanRepository;

	public List<IssueDto> getIssueDetails() {
		List<Issue> issueList = (List<Issue>) issueRepository.findAll();
		List<IssueDto> issueDtoList = new ArrayList<>();

		for (Issue issue : issueList) {
			IssueDto issueDto = new IssueDto();
			BeanUtils.copyProperties(issue, issueDto);
			List<IssuePlanDto> issuePlanDtoList = new ArrayList<>();

			for (IssuePlan issuePlan : issue.getIssuePlans()) {
				IssuePlanDto issuePlanDto = new IssuePlanDto();
				BeanUtils.copyProperties(issuePlan, issuePlanDto);
				issuePlanDto.setIssue(issueDto);
				issuePlanDtoList.add(issuePlanDto);

			}
			issueDto.setIssuePlans(issuePlanDtoList);
			issueDtoList.add(issueDto);
		}
		return issueDtoList;
	}

	public void addIssues(List<IssueDto> issueDtoList) throws ParseException {
		List<Issue> issueList = new ArrayList<>();
		for (IssueDto issueDto : issueDtoList) {
			List<Issue> issueFromDb = issueRepository.findByName(issueDto
					.getName());
			Issue issue = null;
			if (null != issueFromDb && !issueFromDb.isEmpty()) {
				issue = issueFromDb.get(0);
				issue.setDescription(issueDto.getDescription());
				issue.setExpectedWindow(issueDto.getExpectedWindow());
				issue.setSbType(issueDto.getSbType());
				issue.setHardDate(issueDto.getHardDate());
				issue.setCycle(issueDto.getCycle());
				issue.setInterval(issueDto.getInterval());
				issue.setAdDate(issueDto.getAdDate());
				issue.setDeadLineDate(issueDto.getDeadLineDate());

			} else {
				issue = new Issue();
				BeanUtils.copyProperties(issueDto, issue);
			}

			List<IssuePlan> issuePlanList = new ArrayList<>();
			for (IssuePlanDto issuePlanDto : issueDto.getIssuePlans()) {
				IssuePlan issuePlan = null;
				// List<IssuePlan> issuePlanFromDb = issuePlanRepository
				// .findBySerial(issuePlanDto.getSerial());
				// List<IssuePlan> issuePlanFromDb = issuePlanRepository
				// .findBySerialOrderByCreatedDateDesc(issuePlanDto
				// .getSerial());
				List<IssuePlan> issuePlanFromDb = issuePlanRepository
						.findBySerialAndIssueIdOrderByCreatedDateDesc(
								issuePlanDto.getSerial(), issuePlanDto
										.getIssue().getId());

				if (null != issuePlanFromDb && !issuePlanFromDb.isEmpty()) {
					issuePlan = issuePlanFromDb.get(0);
					if (isSameDate(issuePlan.getDueOn(),
							issuePlanDto.getDueOn())) {
						issuePlan.setStatus(issuePlanDto.getStatus());
						issuePlan.setCompliedOn(issuePlanDto.getCompliedOn());
						issuePlan.setDueOn(issuePlanDto.getDueOn());

					} else {
						issuePlan = new IssuePlan();
						BeanUtils.copyProperties(issuePlanDto, issuePlan);
					}
				} else {
					issuePlan = new IssuePlan();
					BeanUtils.copyProperties(issuePlanDto, issuePlan);
				}

				issuePlan.setIssue(issue);
				issuePlan.setCreatedDate(new Date());
				issuePlanList.add(issuePlan);
			}
			issue.setIssuePlans(issuePlanList);
			issue.setCreatedDate(new Date());
			issueList.add(issue);
		}

		issueRepository.save(issueList);
	}

	private boolean isSameDate(Date dateFromDb, Date dateFromInput)
			throws ParseException {
		boolean isSameDate = Boolean.FALSE;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date date1 = sdf.parse(sdf.format(dateFromDb));
		Date date2 = sdf.parse(sdf.format(dateFromInput));
		System.out.println("Date From DB:" + date1);
		System.out.println("Date From Input:" + date2);

		if (date1.compareTo(date2) == 0) {
			isSameDate = Boolean.TRUE;
		}

		return isSameDate;
	}

}
