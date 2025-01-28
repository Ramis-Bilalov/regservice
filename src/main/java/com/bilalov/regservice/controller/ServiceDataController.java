package com.bilalov.regservice.controller;

import com.bilalov.regservice.entity.DeletedRecords;
import com.bilalov.regservice.entity.ServiceData;
import com.bilalov.regservice.repositories.DeletedRecordsRepository;
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
    @Autowired
    private DeletedRecordsRepository deletedRecordsRepository;

    @GetMapping("/list_free")                                               //лист для просмотра реестра для всех пользователей
    public String listRecordsForAll(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "listfree";
    }

    @GetMapping("/form")                                                    //форма внесения первичных данных
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showForm(Model model) {
        ServiceData serviceData = new ServiceData();
        serviceData.setStatus("ЗАЯВКА");
        model.addAttribute("serviceData", serviceData);
        return "form";
    }

    @GetMapping("/form/{id}")                                               //форма внесения даты и времени записи
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        model.addAttribute("serviceData", serviceData);
        return "edit-form";
    }

    @GetMapping("/delete-form/{id}")                                        //форма для подтверждения удаления записи
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteForm(@PathVariable Long id, Model model) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        model.addAttribute("serviceData", serviceData);
        return "deleteform";
    }

    @PostMapping("/processForm")                                            //запись первичной формы
    public String processForm(ServiceData serviceData) {
        serviceDataRepository.save(serviceData);
        return "redirect:/cars/list"; // Перенаправляем на список после сохранения
    }

    @PostMapping("/deleteProcess/{id}")                                     //удаление из основной таблицы и добавление в таблицу удаленных продуктов
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteProcess(@PathVariable Long id) {
        ServiceData sd = serviceDataRepository.findById(id).orElse(null);
        DeletedRecords deletedRecords = new DeletedRecords(sd.getStatus(), sd.getNumber(), sd.getGosnomer(), sd.getCompany(), sd.getInn(), sd.getPlace(), sd.getName(), sd.getComment(), sd.getDate(), sd.getTime());
        deletedRecordsRepository.save(deletedRecords);
        serviceDataRepository.deleteById(id);
        return "redirect:/cars/list"; // Перенаправляем на список после сохранения
    }

    @GetMapping("/list")                                                    //просмотр таблицы записи
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listRecords(Model model) {
        List<ServiceData> records = serviceDataRepository.findAll();
        model.addAttribute("records", records);
        return "list";
    }

    @GetMapping("/deleted-list")                                            //просмотр таблицы удаленной записи
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletedList(Model model) {
        List<DeletedRecords> records = deletedRecordsRepository.findAll();
        model.addAttribute("records", records);
        return "deletedlist";
    }

    @PostMapping("/delete/{id}")                                            //удаление записи - уже не используется
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteRecord(@PathVariable Long id) {
        serviceDataRepository.deleteById(id);
        return "redirect:/cars/list";
    }

    @GetMapping("/deleted/{id}")                                            // удаление удаленной записи
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteDeleted(@PathVariable Long id) {
        deletedRecordsRepository.deleteById(id);
        return "redirect:/cars/deleted-list";
    }

    @PostMapping("/update/{id}")                                            //обновление записи после внесения изменений
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateServiceData(@PathVariable Long id, Model model, ServiceData serviceDataM) {
        ServiceData serviceData = serviceDataRepository.findById(id).orElse(null);
        serviceDataM.setStatus("В ЗАПИСИ");
        serviceDataRepository.save(serviceDataM);
        model.addAttribute("serviceData", serviceData);
        return "redirect:/cars/list";
    }
}