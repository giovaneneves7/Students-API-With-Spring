package br.com.ifba.giovaneneves.sms.web.controller;

//============================================{ IMPORTS }============================================//
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
//============================================{ END IMPORTS }============================================//

@Controller
@RequestMapping("/login")
public class LoginController {

    public String login(Model model){

        return "home/public/login";
    }
}
