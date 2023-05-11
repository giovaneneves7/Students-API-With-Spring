//======================================{ PACKAGE }======================================//
package br.com.ifba.giovaneneves.sms.student.dao;
//======================================{ END PACKAGE }======================================//

//======================================{ IMPORTS }======================================//
import br.com.ifba.giovaneneves.sms.student.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//======================================{ END IMPORTS }======================================//

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
