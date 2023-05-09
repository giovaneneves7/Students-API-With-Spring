//======================================{ PACKAGE }======================================//
package br.com.ifba.giovaneneves.sms.api.resource.student.model;
//======================================{ END PACKAGE }======================================//

//======================================{ IMPORTS }======================================//
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
//======================================{ END IMPORTS }======================================//

@Data
public class StudentResource {

    //======================================{ ATTRIBUTES }======================================//
    @JsonProperty("student_name")
    private String name;

    @JsonProperty("registration_number")
    private String registrationNumber;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("academic_year")
    private String academicYear;

    @JsonProperty("grades")
    private GradeResource grades;

    @JsonProperty("average_grades")
    private String averageGrades;
}
