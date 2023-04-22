package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.Student;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repository.StudentRepository;

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
    public String deleteStudent(@PathVariable("id") long id, Model model) {

        Optional<Student> foundStudent = studentRepository.findById(id);

        if(!foundStudent.isPresent())
            return "redirect:/student/list";

        Student student = foundStudent.get();

        studentRepository.delete(student);

        return "redirect:/student/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {

        Optional<Student> foundStudent = studentRepository.findById(id);

        Student student = foundStudent.get();

        model.addAttribute("student", student);

        return "/edit-student";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @Valid Student student, BindingResult results){

        if(results.hasErrors()){
            student.setId(id);
            return "/edit-student";
        }

        studentRepository.save(student);

        return "redirect:/student/list";
    }
}
