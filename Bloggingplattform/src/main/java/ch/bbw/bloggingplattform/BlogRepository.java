package ch.bbw.bloggingplattform;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

}
