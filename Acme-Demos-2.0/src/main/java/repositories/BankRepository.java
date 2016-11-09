package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

	@Query("select c from Bank c where c.userAccount.id=?1")
	Bank findByBankAccountId(int id);

	@Query("select b from Bank b where (b.commercialName like CONCAT('%',?1,'%'))")
	Collection<Bank> searchByKeyword(String keyword);
	
	@Query("select b from Bank b where b.loans.size=(select max(b2.loans.size) from Bank b2)")
	Collection<Bank> banksWithMoreLoans();

}
