package daw.cookcinando;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.cookcinando.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}