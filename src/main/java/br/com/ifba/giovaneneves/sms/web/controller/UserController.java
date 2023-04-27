package br.com.ifba.giovaneneves.sms.web.controller;

//============================================{ IMPORTS }============================================//
import br.com.ifba.giovaneneves.sms.infrastructure.exceptions.user.UserNotFoundException;
import br.com.ifba.giovaneneves.sms.user.model.User;
import br.com.ifba.giovaneneves.sms.user.dao.UserRepository;
import br.com.ifba.giovaneneves.sms.user.role.dao.RoleRepository;
import br.com.ifba.giovaneneves.sms.user.role.model.Role;

import br.com.ifba.giovaneneves.sms.user.service.UserService;
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
    public final String LOGIN_PAGE = "home/public/login";
    public final String CREATE_NEW_USER_PAGE = "auth/user/public/create-new-user-account";
    public final String USER_LIST_PAGE = "auth/user/adm/users-list";
    public final String NEW_USER_REQUEST = "/user/new";
    public final String USER_LIST_REQUEST = "/user/list";

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    //============================================{ MAP }============================================//

    @GetMapping("/validate")
    public String cofirmLogin(@RequestParam("email") String email, @RequestParam("password") String password){

        Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);

        if(user.isPresent())
            return "redirect:".concat(USER_LIST_REQUEST);

        return LOGIN_PAGE;
    }

    @GetMapping("/new")
    public String createNewUser(Model model){

        model.addAttribute("user", new User());

        return CREATE_NEW_USER_PAGE;
    }
    @PostMapping("/save")
    public String save(@Valid User user, BindingResult results, RedirectAttributes attributes){

        if(results.hasErrors())
            return CREATE_NEW_USER_PAGE;

        Role defaultRole = roleRepository.findByRole("USER");

        List<Role> roles = List.of(defaultRole);


        user.setRoles(roles);

        userRepository.save(user);
        attributes.addFlashAttribute("message", "User successfully saved!");
        return "redirect:".concat(NEW_USER_REQUEST);
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes attributes){

        try{

            User user = userService.findUserById(id);

            userService.delete(user);

            return "redirect:".concat(USER_LIST_PAGE);
        } catch (UserNotFoundException ex){

            attributes.addFlashAttribute("message", ex.getMessage());
            return USER_LIST_PAGE;

        }


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
