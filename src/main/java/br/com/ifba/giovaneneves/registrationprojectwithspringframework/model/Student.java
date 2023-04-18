package br.com.ifba.giovaneneves.registrationprojectwithspringframework.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

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
    public String name;

    @Column(nullable = false, unique = true)
    public String registrationNumber;

    @Column(nullable = false)
    public int age;

    @Column(nullable = false)
    public int academicYear;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    public Grade grade;*/

    @Column(nullable = false)
    public float averageGrades;


}