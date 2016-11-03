package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
