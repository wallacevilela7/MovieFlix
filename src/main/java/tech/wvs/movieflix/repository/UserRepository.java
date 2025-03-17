package tech.wvs.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}