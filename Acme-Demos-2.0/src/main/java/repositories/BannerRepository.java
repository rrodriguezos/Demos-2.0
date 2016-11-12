package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;


@Repository
public interface BannerRepository extends JpaRepository<Banner,Integer>{
	
	@Query("select i.banners from Investor i where i.id=?1")
	Collection<Banner> bannersByInvestor(int investorId);
	

}
