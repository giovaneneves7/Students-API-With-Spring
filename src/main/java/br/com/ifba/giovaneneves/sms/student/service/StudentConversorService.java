package br.com.ifba.giovaneneves.sms.student.service;

import br.com.ifba.giovaneneves.sms.api.resource.student.model.GradeResource;
import br.com.ifba.giovaneneves.sms.api.resource.student.model.StudentResource;
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.BusinessException;
import br.com.ifba.giovaneneves.sms.student.grade.model.Grade;
import br.com.ifba.giovaneneves.sms.student.model.Student;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StudentConversorService {

    private static final String INVALID_BIRTH_DATE = "The date must be valid";
    private static final String WRONG_NUMBER_FORMAT = "The data types are not compatible";

    public Student ConvertStudent(StudentResource studentResource) throws BusinessException{

        Student student = new Student();

        student.setName(studentResource.getName());
        student.setRegistrationNumber(studentResource.getRegistrationNumber());

        student.setBirthDate(checkDate(studentResource.getBirthDate()));
        student.setAcademicYear(convertToInt(studentResource.getAcademicYear()));
        student.setGrade(convertToGrade(studentResource.getGrades()));

        student.setAverageGrades();

        return student;
    }

    private Date checkDate(String stringToBeConverted) throws BusinessException{

        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedDate = dateFormat.parse(stringToBeConverted);
            return convertedDate;

        } catch(ParseException ex){

            throw new BusinessException(INVALID_BIRTH_DATE);

        }

    }

    private int convertToInt(String stringToBeConverted) throws BusinessException{

        try{

            Integer convertedInt = Integer.parseInt(stringToBeConverted);
            return convertedInt;

        }catch(NumberFormatException ex){

            throw new BusinessException(WRONG_NUMBER_FORMAT);
        }
    }

    private Grade convertToGrade(GradeResource gradeResource) throws BusinessException{

        try{

            Grade grades = new Grade();
            grades.setGrade1(Float.parseFloat(gradeResource.getGrade1()));
            grades.setGrade2(Float.parseFloat(gradeResource.getGrade2()));
            grades.setGrade3(Float.parseFloat(gradeResource.getGrade3()));

            return grades;

        } catch(Exception ex){

            throw new BusinessException(WRONG_NUMBER_FORMAT);

        }
    }
}

