package br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
