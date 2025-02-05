package com.bilalov.regservice.controller;


import com.bilalov.regservice.entity.ServiceData;
import com.bilalov.regservice.entity.User;
import com.bilalov.regservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register/")
public class RegistrationController {


    @Autowired
    UserRepository userRepository;

    @PostMapping("/process")
    public String registrationClient(User user) {
        userRepository.save(user);
        return "redirect:/cars/list"; // Перенаправляем на список после сохранения
    }

    @GetMapping("/client")                                                    //форма внесения первичных данных
    public String regForm(Model model) {
        User user = new User();
        user.setRole("ROLE_USER");
        model.addAttribute("user", user);
        return "registration";
    }
}
