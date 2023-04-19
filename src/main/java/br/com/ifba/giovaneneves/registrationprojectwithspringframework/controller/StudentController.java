package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Grade;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/list")
    public String listStudents(Model model){

        model.addAttribute("students", studentRepository.findAll());

        return "students-list";
    }

    @GetMapping("/new")
    public String createNewStudent(Model model){

        model.addAttribute("student", new Student());

        return "create-new-student";
    }
}
