package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Description;

@Repository
public interface DescriptionRepository extends
		JpaRepository<Description, Integer> {

	@Query("select d from Description d where d.demo.id = ?1")
	Collection<Description> descriptionsByDemo(int demoId);

}
