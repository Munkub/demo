package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @GetMapping
    String GetHome(Model model){//возвращает на главную
        model.addAttribute("name", "Tom");
        return "home";
    }
    @PostMapping("/home")
    public String SetNameHome(Model model,
                              @RequestParam(name = "NameInput", required = false, defaultValue = "TestWord")
                              String name1){
        model.addAttribute("name1", name1);
        return "home";
    }
    @GetMapping("/calc")
    public String calculator() {
        return "calc"; // Возвращает страницу калькулятора
    }
    @PostMapping("/calc")
    public String calculate(@RequestParam double num1,
                            @RequestParam double num2,
                            @RequestParam String operation, Model model) {
        double result;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                result = 0;
        }
        model.addAttribute("result", result);
        return "calc";
    }
    @GetMapping("/convert")
    public String convert() {return "convert"; // Возвращает страницу конвертера валют
    }
    @PostMapping("/convert")
    public String calc(Model model,
                       @RequestParam(name = "a", required = false,defaultValue = "0") double a,
                       @RequestParam(name = "val1") String val1,
                       @RequestParam(name = "val2") String val2) {
        double b =0;
        if (val1.equals("RUB") && val2.equals("USD")){
            b = a/90;
        }
        else if (val1.equals("USD") && val2.equals("RUB")) {
            b = a*90;
        }
        else if (val1.equals("JPY") && val2.equals("RUB")) {
            b = a*1.62;
        }
        else if (val1.equals("RUB") && val2.equals("JPY")) {
            b = a/1.62;
        }
        else if (val1.equals("JPY") && val2.equals("USD")) {
            b = a*146.26;
        }
        else if (val1.equals("USD") && val2.equals("JPY")) {
            b = a/146.26;
        }
        else if (val1.equals("RUB") && val2.equals("RUB") || val1.equals("USD") && val2.equals("USD") ||
                val1.equals("JPY") && val2.equals("JPY")){
            b=a;
        }
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("val1", val1);
        model.addAttribute("val2", val2);
        return "convert"; // Возвращает страницу с результатом конвертации
    }

}