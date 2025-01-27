package com.bilalov.regservice.controller;

import com.bilalov.regservice.entity.ServiceData;
import com.bilalov.regservice.repositories.ServiceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("cars/")
public class ServiceDataController {

    @Autowired
    private ServiceDataRepository serviceDataRepository;
    private Long idSave;

    @GetMapping("/list_free")
    public String listRecordsForAll(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "listfree";
    }

    @GetMapping("/form")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showForm(Model model) {
        ServiceData serviceData = new ServiceData();
        serviceData.setStatus("ЗАЯВКА");
        model.addAttribute("serviceData", serviceData);
        return "form";
    }

    @GetMapping("/delete-form/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteForm(@PathVariable Long id, Model model) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        model.addAttribute("serviceData", serviceData);
        return "deleteform";
    }

    @GetMapping("/form/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        model.addAttribute("serviceData", serviceData);
        return "edit-form";
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

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRecord(@PathVariable Long id) {
        serviceDataRepository.deleteById(id);
        return "redirect:/cars/list";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateServiceData(@PathVariable Long id, Model model, ServiceData serviceDataM) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        serviceDataM.setStatus("В ЗАПИСИ");
        serviceDataRepository.save(serviceDataM);
        model.addAttribute("serviceData", serviceData);
        return "redirect:/cars/list";
    }
}