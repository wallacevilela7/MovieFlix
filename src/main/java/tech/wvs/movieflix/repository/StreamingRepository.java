package tech.wvs.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix.entity.Streaming;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}