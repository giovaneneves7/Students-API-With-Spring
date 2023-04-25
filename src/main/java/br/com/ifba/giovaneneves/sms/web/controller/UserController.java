package br.com.ifba.giovaneneves.sms.web.controller;

//============================================{ IMPORTS }============================================//

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//============================================{ END IMPORTS }============================================//

@Controller
@RequestMapping("/user")
public class UserController {

    //============================================{ ATTRIBUTES }============================================//

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    //============================================{ METHODS }============================================//

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

        return "auth/user/public/create-new-user-account";
    }
    @PostMapping("/save")
    public String save(@Valid User user, BindingResult results, RedirectAttributes attributes){

        if(results.hasErrors())
            return "auth/user/public/create-new-user-account";

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
            return "auth/user/adm/users-list";

        User user = foundUser.get();
        userRepository.delete(user);

        return "redirect:auth/user/adm/users-list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){

        Optional<User> foundUser = userRepository.findById(id);

        if(!foundUser.isPresent())
            return "redirect:auth/user/adm/users-list";

        User user = foundUser.get();

        model.addAttribute("user", user);

        return "auth/user/adm/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @Valid User user, BindingResult results){

        if(results.hasErrors()){
            user.setId(id);
            return "auth/user/adm/edit-user";
        }

        userRepository.save(user);

        return "redirect:auth/user/adm/users-list";
    }
    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("users", userRepository.findAll());

        return "auth/user/adm/users-list";
    }
    @GetMapping("/edit-role/{id}")
    public String editRoles(@PathVariable("id") long id, Model model){

        Optional<User> oldUser = userRepository.findById(id);

        if(!oldUser.isPresent())
            return "auth/user/adm/users-list";

        User user = oldUser.get();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", roleRepository.findAll());

        return "auth/user/adm/edit-user-roles";

    }

    @PostMapping("/edit-role/{id}")
    public String editRoles(@PathVariable("id") long userId,
                            @RequestParam(value = "rls", required = false) int[] roles, User user,
                            RedirectAttributes attributes){

        //---+ Checks if at least one role has been selected --+//
        if(roles == null){
            user.setId(userId);
            attributes.addFlashAttribute("message", "You must select at least 1 role");
            return "redirect:/user/edit-role/".concat(String.valueOf(userId));
        } else{
            List<Role> roleList = new ArrayList<>();

            for(int i = 0; i < roles.length; i++){

                long roleId = roles[i];
                Optional<Role> optionalRole = roleRepository.findById(roleId);

                if(optionalRole.isPresent()){
                    Role role = optionalRole.get();
                    roleList.add(role);
                }

            }

            //--+ Saves user with new roles and status in the database +--//
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isPresent()){
                User usr = optionalUser.get();
                usr.setRoles(roleList);
                usr.setActive(user.isActive());
                userRepository.save(usr);
            }
        }

        return "redirect:/user/list";
    }
}
