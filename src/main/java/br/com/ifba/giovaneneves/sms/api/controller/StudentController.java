package br.com.ifba.giovaneneves.sms.api.controller;

//======================================{ IMPORTS }======================================//
import br.com.ifba.giovaneneves.sms.api.resource.student.model.StudentResource;
import br.com.ifba.giovaneneves.sms.infrastructure.facade.Facade;
import br.com.ifba.giovaneneves.sms.student.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//======================================{ END IMPORTS }======================================//

@RestController
@RequestMapping("/api")
public class StudentController {

    //======================================{ ATTRIBUTES }======================================//
    @Autowired
    Facade facade;

    //======================================{ METHODS }======================================//
    /**
     * @return a list of students or the list size if count is present.
     */
    @GetMapping("/students")
    @CrossOrigin(origins = "*")
    public ResponseEntity getAllStudents(@RequestParam(value="count", required = false, defaultValue = "false") Boolean count,
                                         @RequestParam(value="average-grades", required = false, defaultValue = "false") Boolean averageGrades){

        if(count){
            return ResponseEntity.ok(facade.findAllStudents().size());
        }

        if(averageGrades){
            return ResponseEntity.ok(facade.getAverageStudentGrade());
        }

        return ResponseEntity.ok(facade.findAllStudents());

    }

    @GetMapping("/students/student/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Optional<Student>> findById(@PathVariable(value = "id") Long id){

        return ResponseEntity.ok(facade.findStudentById(id));

    }

    @PostMapping("/students/student")
    @CrossOrigin(origins = "*")
    public void save(@RequestBody StudentResource studentResource){

        facade.saveStudent(studentResource);

    }

    @DeleteMapping("/students/student/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){

        if(facade.deleteStudentById(id))
            return ResponseEntity.ok("Success!");
        else
            return ResponseEntity.badRequest().body("Student not found!");
        
    }

}
