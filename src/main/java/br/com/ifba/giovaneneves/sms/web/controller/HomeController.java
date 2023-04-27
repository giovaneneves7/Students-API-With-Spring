package br.com.ifba.giovaneneves.sms.web.controller;

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.user.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
//============================================{ END IMPORTS }============================================//

@Controller
public class HomeController {
    //============================================{ ATTRIBUTES }============================================//
    public final String LOGIN_PAGE = "home/public/login";

    //============================================{ MAP }============================================//
    /**
     * Returns the application index.
     * @param model Object that adds attributes in the View.
     * @return First page that will be displayed to the user.
     */
    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("user", new User());

        return LOGIN_PAGE;
    }

}
