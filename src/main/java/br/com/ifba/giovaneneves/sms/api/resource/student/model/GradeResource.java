//======================================{ PACKAGE }======================================//
package br.com.ifba.giovaneneves.sms.api.resource.student.model;
//======================================{ PACKAGE }======================================//

//======================================{ IMPORTS }======================================//
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//======================================{ END IMPORTS }======================================//

@Data
public class GradeResource {

    //======================================{ ATTRIBUTES }======================================//
    @JsonProperty("grade_1")
    private String grade1;

    @JsonProperty("grade_2")
    private String grade2;

    @JsonProperty("grade_3")
    private String grade3;

}
