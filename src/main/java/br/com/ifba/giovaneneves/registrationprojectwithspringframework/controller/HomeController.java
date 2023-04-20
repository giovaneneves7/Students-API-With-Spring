package br.com.ifba.giovaneneves.registrationprojectwithspringframework.controller;

import br.com.ifba.giovaneneves.registrationprojectwithspringframework.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("user", new User());
        return "login";
    }

}
