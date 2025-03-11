package tech.wvs.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wvs.movieflix.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
