package br.com.ifba.giovaneneves.sms.component;

import br.com.ifba.giovaneneves.sms.user.role.model.Role;
import br.com.ifba.giovaneneves.sms.user.role.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadData implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        List<String> roles = List.of("Student", "Teacher", "Moderator", "Admin");

        for(String roleStr : roles){
            Role role = roleRepository.findByRole(roleStr);

            if(role == null){
                role = new Role(roleStr);
                roleRepository.save(role);
            }
        }

    }
}
