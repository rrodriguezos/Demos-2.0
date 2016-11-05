package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.WhitePaper;

@Repository
public interface WhitePaperRepository extends
		JpaRepository<WhitePaper, Integer> {

	@Query("select i.whitePapers from Investor i where i.id=?1")
	Collection<WhitePaper> whitePapersByInvestor(int investorId);

	@Query("select w from WhitePaper w where (w.title like CONCAT('%',?1,'%'))")
	Collection<WhitePaper> searchByKeyword(String q);

}
