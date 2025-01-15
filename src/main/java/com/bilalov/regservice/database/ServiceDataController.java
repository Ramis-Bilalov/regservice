package com.bilalov.regservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class ServiceDataController {

    @Autowired
    private ServiceDataRepository serviceDataRepository;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("serviceData", new ServiceData());
        return "form";
    }

    @PostMapping("/processForm")
    public String processForm(ServiceData serviceData) {
        serviceDataRepository.save(serviceData);
        return "redirect:/list"; // Перенаправляем на список после сохранения
    }

    @GetMapping("/list")
    public String listRecords(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        serviceDataRepository.deleteById(id);
        return "redirect:/list";
    }
}