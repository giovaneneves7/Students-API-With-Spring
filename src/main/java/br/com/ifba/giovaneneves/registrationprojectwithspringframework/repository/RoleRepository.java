package br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     *
     * @param role The role that will be sought.
     * @return The paper found, or void if it does not exist.
     */
    Role findByRole(String role);
}
