//======================================{ PACKAGE }======================================//
package br.com.ifba.giovaneneves.sms.student.model;
//======================================{ END PACKAGE }======================================//

//======================================{ IMPORTS }======================================//
import br.com.ifba.giovaneneves.sms.infrastructure.model.AbstractEntity;
import br.com.ifba.giovaneneves.sms.student.grade.model.Grade;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//======================================{ END IMPORTS }======================================//
/**
 *
 * @author GiovaneNeves
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AbstractEntity implements Serializable
{

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

    //======================================{ METHODS }======================================//
    public void setAverageGrades()
    {

       if(this.getGrade().getGrade1() == 0.0f && this.getGrade().getGrade2() == 0.0f  && this.getGrade().getGrade3() == 0.0f)
       {

           this.averageGrades = 0.0f;

       } else {

           float sum;
           sum = (this.getGrade().getGrade1() + this.getGrade().getGrade2() + this.getGrade().getGrade3());
           averageGrades = (sum / 3.0f);

       }

    }

}