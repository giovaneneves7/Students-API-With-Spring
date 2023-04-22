package br.com.ifba.giovaneneves.registrationprojectwithspringframework.service;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.dao.StudentDAOImpl;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.ExistingRegistrationNumberException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.InvalidAgeException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.InvalidRegistrationNumberException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.StudentNotFoundException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Data
public class StudentService {

    //============================================{ ATTRIBUTES }============================================//

    private final static String REGISTRATION_NUMBER_ALREADY_EXISTS = "The specified registration number already exists in the database";
    private final static String STUDENT_NOT_FOUND = "The specified student could not be found";
    private final static String REGISTRATION_NUMBER_INVALID_LENGTH = "The registration number must have exactly 4 digits.";
    private final static String INVALID_AGE = "The student must be 13 years or older to be registered";

    @Autowired
    private StudentRepository studentRepository;

    //============================================{ GETTERS AND SETTERS }============================================//

    public StudentService(){

    }


    //============================================{ METHODS }============================================//
    /**
     *
     * Inserts a student in the database
     * @param student to be added to the database.
     */
    public void saveStudent(Student student) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidAgeException {

        //--+ Checks if there is already a student with the same enrollment number in the database +--//
        if(studentRepository.findAll().stream()
                .anyMatch(s -> s.getRegistrationNumber().equals(student.getRegistrationNumber())))
            throw new ExistingRegistrationNumberException(REGISTRATION_NUMBER_ALREADY_EXISTS);

        //--+ Checks that the license plate number has only 4 digits +--//
        if(student.getRegistrationNumber().length() > 4)
            throw new InvalidRegistrationNumberException(REGISTRATION_NUMBER_INVALID_LENGTH);

        //--+ Checks if the student is older than 13  +--//
     /*   if(student.getAge() < 13)
            throw new InvalidAgeException(INVALID_AGE);*/

        studentRepository.save(student);

    }

    /**
     *
     * Search a student in the database
     * @param id of the student to be searched.
     * @return student with the specified ID.
     */
    public Optional<Student> findStudentById(int id) throws StudentNotFoundException{
        Optional<Student> foundStudent = studentRepository.findById(id);

        //--+ Checks if there is a student with the specified id +--//
        if(!foundStudent.isPresent())
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);

        return foundStudent;

    }

    /**
     * List all students.
     * @return a list with all students in the database.
     */
    public List<Student> listAllStudents(){

        return this.studentRepository.findAll();

    }

    /**
     *
     * @param id of the student to be removed from the database.
     * @return true if the student exists, false otherwise.
     */
    public void removeStudent(int id) throws StudentNotFoundException {

        Optional<Student> foundStudent = studentRepository.findById(id);

        //--+ Checks if there is a student with the specified id +--//
        if(!foundStudent.isPresent())
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);
        Student student = foundStudent.get();

        this.studentRepository.deleteById(student.getId());

    }

    /**
     *
     * @param student to be updated.
     * @return true if the student exists in the database and the update was successful, false otherwise.
     */
    public void updateStudent(Student student) throws StudentNotFoundException {

        //--+ Checks if the student is contained in the database +--//
        if(!studentRepository.findAll().contains(student))
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);

        this.studentRepository.save(student);

    }


}
