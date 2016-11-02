package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Instalment;

@Repository
public interface InstalmentRepository extends JpaRepository<Instalment, Integer> {
	
	@Query("select i.instalments from Investment i where i.id=?1")
	Collection<Instalment> findByInvestment(int investmentId);

	/***************** Principio *****************/
	@Query("select round(avg(i.amount), 2) from Instalment i")
	Double  avgMoneyInvestInDemos();
	
	@Query("select round(avg(i.instalments.size), 2) from Investment i")
	Double avgNumberInstalmentsPerInvestment();
	/***************** Fin *****************/
}
