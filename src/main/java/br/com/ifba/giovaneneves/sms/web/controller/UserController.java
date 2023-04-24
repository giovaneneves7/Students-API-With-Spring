package br.com.ifba.giovaneneves.sms.web.controller;


import br.com.ifba.giovaneneves.sms.user.model.User;
import br.com.ifba.giovaneneves.sms.user.dao.UserRepository;

import br.com.ifba.giovaneneves.sms.user.role.dao.RoleRepository;
import br.com.ifba.giovaneneves.sms.user.role.model.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/validate")
    public String cofirmLogin(@RequestParam("email") String email, @RequestParam("password") String password){

        Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);

        if(user.isPresent())
            return "redirect:/user/list";

        return "home/public/login";
    }

    @GetMapping("/new")
    public String createNewUser(Model model){

        model.addAttribute("user", new User());

        return "user/public/create-new-user-account";
    }
    @PostMapping("/save")
    public String save(@Valid User user, BindingResult results, RedirectAttributes attributes){

        if(results.hasErrors())
            return "user/public/create-new-user-account";

        Role defaultRole = roleRepository.findByRole("USER");

        List<Role> roles = List.of(defaultRole);


        user.setRoles(roles);

        userRepository.save(user);
        attributes.addFlashAttribute("message", "User successfully saved!");
        return "redirect:/user/new";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){

        Optional<User> foundUser = userRepository.findById(id);

        if(!foundUser.isPresent())
            return "user/adm/users-list";

        User user = foundUser.get();
        userRepository.delete(user);

        return "redirect:user/adm/users-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){

        Optional<User> foundUser = userRepository.findById(id);

        if(!foundUser.isPresent())
            return "redirect:user/adm/users-list";

        User user = foundUser.get();

        model.addAttribute("user", user);

        return "user/adm/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @Valid User user, BindingResult results){

        if(results.hasErrors()){
            user.setId(id);
            return "user/adm/edit-user";
        }

        userRepository.save(user);

        return "redirect:user/adm/users-list";
    }
    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("users", userRepository.findAll());

        return "user/adm/users-list";
    }
    @GetMapping("/edit-role/{id}")
    public String editRoles(@PathVariable("id") long id, Model model){

        Optional<User> oldUser = userRepository.findById(id);

        if(!oldUser.isPresent())
            return "user/adm/users-list";

        User user = oldUser.get();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", roleRepository.findAll());

        return "user/adm/edit-user-roles";

    }

    @PostMapping("/edit-role/{id}")
    public String editRoles(@PathVariable("id") long userId,
                            @RequestParam(value = "rls", required = false) int roles[], User user,
                            RedirectAttributes attributes){

        if(roles == null){
            user.setId(userId);
            attributes.addFlashAttribute("message", "You must select at least 1 role");
            return "redirect:/user/edit-role/" + userId;
        }


        return "";
    }
}
