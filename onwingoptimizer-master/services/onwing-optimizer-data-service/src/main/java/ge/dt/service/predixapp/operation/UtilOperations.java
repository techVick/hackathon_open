package ge.dt.service.predixapp.operation;

import ge.dt.service.predixapp.repository.AirCraftPlanRepository;
import ge.dt.service.predixapp.repository.EngineRepository;
import ge.dt.service.predixapp.repository.IssueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilOperations {

	@Autowired
	private AirCraftPlanRepository airCraftPlanRepository;

	@Autowired
	private EngineRepository engineRepository;

	@Autowired
	private IssueRepository issueRepository;

	public void deleteAllRecords() {

		issueRepository.deleteAll();
		airCraftPlanRepository.deleteAll();
		engineRepository.deleteAll();

	}

}
