package com.bilalov.regservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("cars/")
public class ServiceDataController {

    @Autowired
    private ServiceDataRepository serviceDataRepository;

    @GetMapping("/list_free")
    public String listRecordsForAll(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "listfree";
    }

    @GetMapping("/form")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showForm(Model model) {
        model.addAttribute("serviceData", new ServiceData());
        return "form";
    }

    @PostMapping("/processForm")
    public String processForm(ServiceData serviceData) {
        serviceDataRepository.save(serviceData);
        return "redirect:/cars/list"; // Перенаправляем на список после сохранения
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listRecords(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "list";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRecord(@PathVariable Long id) {
        serviceDataRepository.deleteById(id);
        return "redirect:/cars/list";
    }
}