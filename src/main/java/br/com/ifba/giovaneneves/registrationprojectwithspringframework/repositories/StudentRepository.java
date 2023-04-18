package br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
