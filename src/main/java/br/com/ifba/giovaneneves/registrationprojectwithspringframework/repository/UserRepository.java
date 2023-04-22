package br.com.ifba.giovaneneves.registrationprojectwithspringframework.repository;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
