package br.com.ifba.giovaneneves.registrationprojectwithspringframework;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationProjectWithSpringFrameworkApplication {

    StudentRepository studentRepository;
    public RegistrationProjectWithSpringFrameworkApplication(StudentRepository sr){
        this.studentRepository = sr;
    }
    public static void main(String[] args) {

        SpringApplication.run(RegistrationProjectWithSpringFrameworkApplication.class, args);

    }

}
