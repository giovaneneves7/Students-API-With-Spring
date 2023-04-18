package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public String listStudents(Model model){

        String tableName = "Students List";
        System.out.println("Chegou aqui");
        model.addAttribute("tableStudents", tableName);
        System.out.println("Chegou aqui também ");
        model.addAttribute("students", studentRepository.findAll());
        System.out.println("Chegou aqui também 2");

        for(Student student : studentRepository.findAll()){
            System.out.println("Opa, olha o: " + student.getName());
        }

        return "students";
    }

    @RequestMapping("/")
    public String index(){
        return "students";
    }
}
