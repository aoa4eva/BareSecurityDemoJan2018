package me.afua.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    UserClassRepo userRepository;

    @Autowired
    RoleRepo roleRepository;

    @RequestMapping("/")
    public String showIndex()
    {
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newUser",new UserClass());
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") UserClass newUser, BindingResult result, Model model)
    {

        if(result.hasErrors())
        {
            System.out.println(result.toString());
            return "register";
        }
        else{
             //Create a new ordinary user
             model.addAttribute(newUser.getUsername()+" created");
             UserRole r = roleRepository.findByRole("USER");
             userRepository.save(newUser);
             newUser.addRole(r);
             userRepository.save(newUser);
             return "redirect:/";
        }
    }
}
