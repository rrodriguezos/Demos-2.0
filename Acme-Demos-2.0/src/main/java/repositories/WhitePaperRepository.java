package repositories;

import java.util.Collection;
import java.util.Date;

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
	
	@Query("select avg(i.whitePapers.size) from Investor i")
	Double avgWhitePapersPerInvestor();


	@Query("select w from WhitePaper w where (w.title like CONCAT('%',?1,'%') and w.publishedDate<=?3 and w.publishedDate>=?2)")
	Collection<WhitePaper> searchByKeywordWithRange(String q, Date date1,
			Date date2);

}
