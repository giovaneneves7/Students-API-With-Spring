package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student.StudentNotFoundException;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/list")
    public String listStudents(Model model){

        model.addAttribute("students", studentRepository.findAll());

        return "/students-list";
    }

    @GetMapping("/new")
    public String createNewStudent(Model model){

        model.addAttribute("student", new Student());

        return "/create-new-student";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid Student student, BindingResult results, RedirectAttributes attributes){

        if(results.hasErrors()){
            return "/create-new-student";
        }

        studentRepository.save(student);
        attributes.addFlashAttribute("message", "Student successfully saved!");
        return "redirect:/student/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) throws StudentNotFoundException {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Invalid Id!"));

        studentRepository.delete(student);

        return "redirect:/student/list";
    }
}
