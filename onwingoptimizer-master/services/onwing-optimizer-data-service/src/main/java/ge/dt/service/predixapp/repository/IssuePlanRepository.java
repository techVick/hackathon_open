package ge.dt.service.predixapp.repository;

import ge.dt.service.predixapp.model.IssuePlan;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuePlanRepository extends CrudRepository<IssuePlan, Long> {

	List<IssuePlan> findBySerialOrderByCreatedDateDesc(long serial);

	// List<IssuePlan> findBySerial(long serial);

	List<IssuePlan> findBySerialAndIssueIdOrderByCreatedDateDesc(long serial,
			long issueId);

}
