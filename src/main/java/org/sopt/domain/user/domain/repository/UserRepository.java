package org.sopt.domain.user.domain.repository;

import java.util.Optional;

import org.sopt.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByAuthor(String author);

	Optional<User> findByAuthor(String author);
}
