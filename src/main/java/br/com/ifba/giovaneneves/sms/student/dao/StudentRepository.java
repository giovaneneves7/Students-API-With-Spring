package br.com.ifba.giovaneneves.sms.student.dao;

import br.com.ifba.giovaneneves.sms.student.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
