package br.com.ifba.giovaneneves.registrationprojectwithspringframework.service;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.ExistingRegistrationNumberException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.InvalidAgeException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.InvalidRegistrationNumberException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.StudentNotFoundException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import org.springframework.stereotype.Service;

import java.util.List;

public interface IStudentService {

    /**
     *
     * Inserts a student in the database
     * @param student to be added to the database.
     */
    boolean saveStudent(Student student) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidAgeException;

    /**
     *
     * Search a student in the database
     * @param id of the student to be searched.
     * @return student with the specified ID.
     */
    Student findStudentById(long id) throws StudentNotFoundException;


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
    boolean deleteStudentById(long id) throws StudentNotFoundException;

    /**
     *
     * @param student to be updated.
     * @return true if the student exists in the database and the update was successful, false otherwise.
     */
    public boolean updateStudent(Student student) throws StudentNotFoundException;
}
