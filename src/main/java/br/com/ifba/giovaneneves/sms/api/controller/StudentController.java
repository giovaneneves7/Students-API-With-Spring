package br.com.ifba.giovaneneves.sms.api.controller;

//======================================{ IMPORTS }======================================//
import br.com.ifba.giovaneneves.sms.api.resource.student.model.StudentResource;
import br.com.ifba.giovaneneves.sms.infrastructure.facade.Facade;
import br.com.ifba.giovaneneves.sms.student.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;
import java.util.List;
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
    public ResponseEntity getAllStudents(@RequestParam(value="count", required = false, defaultValue = "false") Boolean count){

        if(count){
            return ResponseEntity.ok(facade.findAllStudents().size());
        }

        return ResponseEntity.ok(facade.findAllStudents());

    }

    @GetMapping("/students/average-grades")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Double> getAverageGrades(){

        List<Student> students = facade.findAllStudents();
        double average;
        double total = students.stream().mapToDouble(Student::getAverageGrades).sum();
        average = (total / 3);

        DecimalFormat decimalFormat = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        average = Double.parseDouble(decimalFormat.format(average));

        return ResponseEntity.ok(average);
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
    public void delete(@PathVariable(value = "id") Long id){

        //facade.(id);
        
    }

}
