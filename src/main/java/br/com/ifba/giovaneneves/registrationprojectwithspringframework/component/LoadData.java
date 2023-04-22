package br.com.ifba.giovaneneves.registrationprojectwithspringframework.component;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Role;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repository.RoleRepository;
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
