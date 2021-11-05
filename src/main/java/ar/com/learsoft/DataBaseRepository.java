package ar.com.learsoft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseRepository extends JpaRepository<DataBase, Long> {

}
