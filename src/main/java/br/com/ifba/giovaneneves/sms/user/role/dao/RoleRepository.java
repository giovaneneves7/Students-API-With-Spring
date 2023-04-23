package br.com.ifba.giovaneneves.sms.user.role.dao;

import br.com.ifba.giovaneneves.sms.user.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     *
     * @param role The role that will be sought.
     * @return The paper found, or void if it does not exist.
     */
    Role findByRole(String role);
}
