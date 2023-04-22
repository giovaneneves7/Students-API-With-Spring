package br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
