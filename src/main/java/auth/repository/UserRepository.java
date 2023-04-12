package auth.repository;

import auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndActiveTrue(String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
