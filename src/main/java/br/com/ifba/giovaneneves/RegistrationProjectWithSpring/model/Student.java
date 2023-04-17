package br.com.ifba.giovaneneves.RegistrationProjectWithSpring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 *
 * @author GiovaneNeves
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@Data
public class Student extends AbstractEntity{

    //============================================{ ATTRIBUTES }============================================//
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private int academicYear;

    @Column(nullable = false)
    private float[] grades = new float[3];

    @Column(nullable = false)
    private float averageGrades;


}