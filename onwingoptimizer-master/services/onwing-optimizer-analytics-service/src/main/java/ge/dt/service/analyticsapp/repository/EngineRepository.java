package ge.dt.service.analyticsapp.repository;

import ge.dt.service.analyticsapp.model.Engine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends CrudRepository<Engine, Long> {

	Engine findBySerial(long serialId);

}
