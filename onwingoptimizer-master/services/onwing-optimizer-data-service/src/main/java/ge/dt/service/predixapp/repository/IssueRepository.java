package ge.dt.service.predixapp.repository;

import ge.dt.service.predixapp.model.Issue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

	List<Issue> findByName(String name);

}
