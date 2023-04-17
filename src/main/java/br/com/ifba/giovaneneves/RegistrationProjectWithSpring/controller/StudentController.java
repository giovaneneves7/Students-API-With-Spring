package br.com.ifba.giovaneneves.RegistrationProjectWithSpring.controller;

import br.com.ifba.giovaneneves.RegistrationProjectWithSpring.model.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {


    @GetMapping("/students")
    public String listStudents(Model model){

        List<Student> students = FacadeInstance.getInstance().listAllStudents();
        model.addAttribute("students", students);

        return "html/students";
    }

    @RequestMapping("/")
    public String index(){
        return "html/students";
    }
}
