package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Investor;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {

	@Query("select i from Investor i where i.userAccount.id=?1")
	Investor findByInvestorAccountId(int userAccountId);
	
	@Query("select distinct i from Investor i join i.whitePapers w " +
			"where ((w.size > (select avg(i2.whitePapers.size) from Investor i2)) and " +
			"((MONTH(w.publishedDate) > (MONTH(CURRENT_TIMESTAMP)-3))) and (YEAR(w.publishedDate) = (YEAR(CURRENT_TIMESTAMP))))")
	Collection<Investor> investorWithMoreAvgWhitePaperPerInvestorLastQuarter();

}
