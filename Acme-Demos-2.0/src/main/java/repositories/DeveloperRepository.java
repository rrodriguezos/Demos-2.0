package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Developer;
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	@Query("select d from Developer d where d.userAccount.id=?1")
	Developer findByDeveloperAccountId(int userAccountId);

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Developer findByUserAccountId(int userAccountId);
	
	@Query("select distinct(d.developer) from Demo d where d.comments.size > (select avg(d.comments.size) from Demo d)")
	Collection<Developer> developersMoreCommentsThanAvg();

}
