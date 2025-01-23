package com.bilalov.regservice.controller;

import com.bilalov.regservice.database.PostgresConnect;
import com.bilalov.regservice.models.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CarsController {

    @GetMapping("/car")
    public String showCars(Model model) throws SQLException {
        PostgresConnect dbService = new PostgresConnect();
        List<Car> cars = dbService.getCars();
        model.addAttribute("cars", cars);
        return "cars";
    }
}