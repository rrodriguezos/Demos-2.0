package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

	@Query("select r from Resource r where r.demo.id = ?1")
	Collection<Resource> resourcesByDemo(int demoId);

}
