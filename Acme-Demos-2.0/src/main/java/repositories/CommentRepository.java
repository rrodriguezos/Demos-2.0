package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;
import domain.Demo;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.demo.id = ?1")
	Collection<Comment> findCommentsByDemoId(int demoId);

	@Query("select avg(d.comments.size) from Demo d where d.developer.id =?1")
	Double averageCommentsPerDemoByDeveloperId(int developerId);

	@Query("select avg(c.stars) from Comment c where c.demo.developer.id = ?1")
	Double averageStarsPerDemoByDeveloper(int developerId);

	@Query("select c.demo from Comment c where c.demo.developer.id = ?1 group by c.demo order by avg(c.stars) desc")
	Collection<Demo> demoSortedAverageNumberStarsByDeveloper(int developerId);
	
	@Query("select avg(d.comments.size) from Demo d")
	Double averageCommentsPerDemo();
	
    @Query("select sum(c.stars) from Comment c join c.demo d)/count(d) * 1.0)  from Demo d")
    Double averageStarsPerDemo();

    @Query("select c.demo from Comment c Group by c.demo order by avg(c.stars) desc")
    Collection<Demo> demoSortedAverageNumberStars();

}
