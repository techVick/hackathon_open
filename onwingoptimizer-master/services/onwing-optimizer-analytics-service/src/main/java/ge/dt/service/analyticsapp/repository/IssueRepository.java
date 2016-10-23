package ge.dt.service.analyticsapp.repository;

import ge.dt.service.analyticsapp.model.Issue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;
import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

	List<Issue> findByName(String name);

}
