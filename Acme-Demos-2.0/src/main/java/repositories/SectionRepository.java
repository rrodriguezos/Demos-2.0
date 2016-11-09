package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

	@Query("select w.sections from WhitePaper w where w.id=?1")
	Collection<Section> sectionsByWhitePaper(int whitePaperId);
	
	@Query("select min(w.sections.size)" +
			"from WhitePaper w where (MONTH(w.publishedDate) > (MONTH(CURRENT_TIMESTAMP)-3))) " +
			"and (YEAR(w.publishedDate) = (YEAR(CURRENT_TIMESTAMP))))")
	Integer minSectionsPerWhitePaperLastQuarter();
	
	@Query("select max(w.sections.size)" +
			"from WhitePaper w where (MONTH(w.publishedDate) > (MONTH(CURRENT_TIMESTAMP)-3))) " +
			"and (YEAR(w.publishedDate) = (YEAR(CURRENT_TIMESTAMP))))")
	Integer maxSectionsPerWhitePaperLastQuarter();
	
	@Query("select avg(w.sections.size)" +
			"from WhitePaper w where (MONTH(w.publishedDate) > (MONTH(CURRENT_TIMESTAMP)-3))) " +
			"and (YEAR(w.publishedDate) = (YEAR(CURRENT_TIMESTAMP))))")
	Double avgSectionsPerWhitePaperLastQuarter();
	
}
