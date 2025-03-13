package tech.wvs.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
