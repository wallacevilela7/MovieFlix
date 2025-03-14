package tech.wvs.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix.entity.Category;
import tech.wvs.movieflix.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByCategories(List<Category> categories);
}
