package br.com.ifba.giovaneneves.registrationprojectwithspringframework.dao;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import java.util.List;

public class StudentDAOImpl extends StudentDAO{

    /**
     * List all students.
     * @return a list with all students in the database.
     */
    @Override
    public List<Student> listStudents(){

        List<Student> studentList = null;
        studentList = StudentDAO.getEntityManager().createQuery("SELECT s FROM Student s", Student.class).getResultList();

        return studentList;
    }

}
