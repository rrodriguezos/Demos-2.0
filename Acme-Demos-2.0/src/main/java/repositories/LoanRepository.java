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
	

}
