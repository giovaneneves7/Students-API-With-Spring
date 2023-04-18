package br.com.ifba.giovaneneves.registrationprojectwithspringframework.dao;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import java.util.List;

public abstract class StudentDAO extends GenereicDAO{

    //============================================{ METHODS }============================================//
    /**
     * List all students.
     * @return a list with all students in the database.
     */
    public abstract List<Student> listStudents();

}
