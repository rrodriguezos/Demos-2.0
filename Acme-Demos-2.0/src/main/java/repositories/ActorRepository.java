package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{
	//Find actor by user account
	@Query("select a from Actor a where a.userAccount.id=?1")
	Actor findByUserAccountId(int userAccountId);
}
