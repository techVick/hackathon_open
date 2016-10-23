package ge.dt.service.analyticsapp.repository;

import ge.dt.service.analyticsapp.model.IssuePlan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuePlanRepository extends CrudRepository<IssuePlan, Long> {

}
