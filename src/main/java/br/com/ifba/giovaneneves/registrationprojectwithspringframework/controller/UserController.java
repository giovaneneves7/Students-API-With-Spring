package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;


import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.User;
import br.com.ifba.giovaneneves.registrationprojectwithspringframework.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/validate")
    public String cofirmLogin(@RequestParam("email") String email, @RequestParam("password") String password){

        Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);

        if(user.isPresent())
            return "redirect:/student/list";

        return "/login";
    }
}
