package br.com.ifba.giovaneneves.registrationprojectwithspringframework.model;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Basic;
import jakarta.persistence.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @NotNull
    @Size(min = 4, message = "Student name cannot be less than 4 characters")
    public String name;

    @Column(nullable = false, unique = true)
    @Size(max = 4, message = "Registration Number cannot have more than 4 characters")
    public String registrationNumber;

    @Basic
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotNull
    public Date birthDate;

    @NotNull
    public int academicYear;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    public Grade grade;

    @NotNull
    public float averageGrades;


}