package ge.dt.service.predixapp.repository;

import ge.dt.service.predixapp.model.AirCraftPlan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.List;

@Repository
public interface AirCraftPlanRepository extends
		CrudRepository<AirCraftPlan, Long> {

	List<AirCraftPlan> findByAssetSerial(String assetserial);

}
