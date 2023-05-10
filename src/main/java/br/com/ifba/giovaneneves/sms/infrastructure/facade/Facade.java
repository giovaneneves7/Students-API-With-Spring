//============================================{ PACKAGE }============================================//
package br.com.ifba.giovaneneves.sms.infrastructure.facade;
//============================================{ END PACKAGE }============================================//

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.api.resource.student.model.StudentResource;
import br.com.ifba.giovaneneves.sms.student.model.Student;
import br.com.ifba.giovaneneves.sms.student.service.StudentService;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.StudentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//============================================{ END IMPORTS }============================================//

@Service
public class Facade implements IFacade {

    //============================================{ ATTRIBUTES }============================================//
    @Autowired
    private StudentService studentService;

    //============================================{ STUDENT }============================================//
    public Facade(){
        this.studentService = new StudentService();
    }

    /**
     *
     * Inserts a student int the database
     * @param studentResource The student resource to be added to the database.
     */
    @Override
    public boolean saveStudent(StudentResource studentResource){
        return studentService.save(studentResource);
    }

    /**
     *
     * Search a student in the database
     * @param id of the student to be searched.
     * @return student with the specified ID, null otherwise.
     */
    @Override
    public Optional<Student> findStudentById(long id){
        return studentService.findStudentById(id);
    }

    /**
     * List all students.
     * @return a list with all students in the database.
     */
    @Override
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    /**
     *
     * @param id of the student to be removed from the database.
     * @return true if the student exists, false otherwise.
     */
    @Override
    public boolean deleteStudentById(long id){
        return studentService.deleteById(id);
    }

    /**
     *
     * @param student to be updated.
     * @return true if the student exists in the database and the update was successful, false otherwise.
     */
    @Override
    public boolean updateStudent(Student student) throws StudentNotFoundException {
        return studentService.updateStudent(student);
    }

    /**
     *
     * @return Average grade point of all students.
     */
    @Override
    public double getAverageStudentGrade() {
        return studentService.getAverageGrades();
    }


}
