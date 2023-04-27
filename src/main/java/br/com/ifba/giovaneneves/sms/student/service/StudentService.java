package br.com.ifba.giovaneneves.sms.student.service;

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.ExistingRegistrationNumberException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.InvalidAgeException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.InvalidRegistrationNumberException;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student.StudentNotFoundException;
import br.com.ifba.giovaneneves.sms.student.dao.StudentRepository;
import br.com.ifba.giovaneneves.sms.student.model.Student;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
//============================================{ END IMPORTS }============================================//

@Data
@Service
public class StudentService implements IStudentService {


    //============================================{ ATTRIBUTES }============================================//

    private final static String REGISTRATION_NUMBER_ALREADY_EXISTS = "The specified registration number already exists in the database";
    private final static String STUDENT_NOT_FOUND = "The specified student could not be found";
    private final static String REGISTRATION_NUMBER_INVALID_LENGTH = "The registration number must have exactly 4 digits.";
    private final static String INVALID_AGE = "The student must be 13 years or older to be registered";

    @Autowired
    private StudentRepository studentRepository;

    //============================================{ METHODS }============================================//
    /**
     *
     * Inserts a student in the database
     * @param student to be added to the database.
     */
    public boolean saveStudent(Student student) throws ExistingRegistrationNumberException, InvalidRegistrationNumberException, InvalidAgeException {

        //--+ Checks if there is already a student with the same enrollment number in the database +--//
        if(studentRepository.findAll().stream()
                .anyMatch(s -> s.getRegistrationNumber().equals(student.getRegistrationNumber())))
            throw new ExistingRegistrationNumberException(REGISTRATION_NUMBER_ALREADY_EXISTS);

        //--+ Checks that the license plate number has only 4 digits +--//
        if(student.getRegistrationNumber().length() > 4)
            throw new InvalidRegistrationNumberException(REGISTRATION_NUMBER_INVALID_LENGTH);

        //--+ Checks if the student is older than 13  +--//
        if((LocalDate.now().getYear() - student.getBirthDate().getYear()) < 13)
            throw new InvalidAgeException(INVALID_AGE);

        studentRepository.save(student);

        return true;

    }

    /**
     *
     * Search a student in the database
     * @param id The ID of the student to be searched.
     * @return student with the specified ID.
     */
    public Student findStudentById(long id) throws StudentNotFoundException{
        Optional<Student> foundStudent = this.studentRepository.findById(id);

        //--+ Checks if there is a student with the specified id +--//
        if(!foundStudent.isPresent())
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);

        return foundStudent.get();

    }

    /**
     * List all students.
     * @return a list with all students in the database.
     */
    public List<Student> findAllStudents(){

        return this.studentRepository.findAll();

    }

    /**
     *
     * @param id of the student to be removed from the database.
     * @return true if the student exists, false otherwise.
     */
    public boolean deleteStudentById(long id) throws StudentNotFoundException {

        Optional<Student> foundStudent = studentRepository.findById(id);

        //--+ Checks if there is a student with the specified id +--//
        if(!foundStudent.isPresent())
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);

        Student student = foundStudent.get();

        this.studentRepository.deleteById(student.getId());

        return true;
    }


    /**
     *
     * @param student to be updated.
     * @return true if the student exists in the database and the update was successful, false otherwise.
     */
    public boolean updateStudent(Student student) throws StudentNotFoundException {

        //--+ Checks if the student is contained in the database +--//
        if(!studentRepository.findAll().contains(student))
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);

        this.studentRepository.save(student);

        return true;
    }


}
