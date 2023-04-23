package br.com.ifba.giovaneneves.sms.web.controller.facade;

import br.com.ifba.giovaneneves.sms.student.model.Student;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.ExistingRegistrationNumberException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.InvalidAgeException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.InvalidRegistrationNumberException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.StudentNotFoundException;

import java.util.List;

public interface IFacade {

    //============================================{ STUDENT METHODS }============================================//

    /**
     *
     * Inserts a student int the database
     * @param student to be added to the database.
     */
    boolean saveStudent(Student student) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidAgeException;

    /**
     *
     * Search a student in the database
     * @param id of the student to be searched.
     * @return student with the specified ID, null otherwise.
     */
    Student findStudentById(int id) throws StudentNotFoundException;

    /**
     * List all students.
     * @return a list with all students in the database.
     */
    List<Student> findAllStudents();

    /**
     *
     * @param id of the student to be removed from the database.
     * @return true if the student exists, false otherwise.
     */
    boolean removeStudent(int id) throws StudentNotFoundException;

    /**
     *
     * @param student to be updated.
     * @return true if the student exists in the database and the update was successful, false otherwise.
     */
    boolean updateStudent(Student student) throws StudentNotFoundException;
}
