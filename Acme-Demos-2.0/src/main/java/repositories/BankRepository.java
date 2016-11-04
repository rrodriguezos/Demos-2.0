package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

	@Query("select c from Bank c where c.userAccount.id=?1")
	Bank findByBankAccountId(int id);

}
