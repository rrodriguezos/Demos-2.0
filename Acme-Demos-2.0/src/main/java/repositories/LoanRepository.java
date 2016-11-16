package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	@Query("select l from Loan l where l.bank.id = ?1")
	Collection<Loan> findLoansByBank(int bankId);
	
	@Query("select d.loans from Developer d where d.id= ?1")
	Collection<Loan> findLoansByDev(int devId);
	
	@Query("select avg(l.size) from Bank b join b.loans l where l.status='PENDING'")
	Double avgPendingLoansPerBank();
	
	@Query("select (count(l) * 1.0 /(select count(f) from Bank b2 join b2.loans f where f.status = 'DENIED') )  from Bank b join b.loans l where l.status = 'APPROVED'")
	Double ratioApprovedToDeniedLoansPerBank();
	
	@Query("select (count(l) * 1.0 /(select count(f) from Developer b2 join b2.loans f where b2.id =?1 and f.status = 'DENIED') )  from Developer b join b.loans l where b.id =?1 and  l.status = 'APPROVED'")
	Double ratioApprovedToDeniedLoansPerDeveloper(int devloperId);

}
