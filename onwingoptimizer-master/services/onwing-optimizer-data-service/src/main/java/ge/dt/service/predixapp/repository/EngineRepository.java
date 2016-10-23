package ge.dt.service.predixapp.repository;

import ge.dt.service.predixapp.model.Engine;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends CrudRepository<Engine, Long> {

	List<Engine> findBySerial(long serial);

}
