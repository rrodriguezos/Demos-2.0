package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.WhitePaper;

@Repository
public interface WhitePaperRepository extends JpaRepository<WhitePaper, Integer> {

}
