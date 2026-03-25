package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    @GetMapping("/detalles_info")
    public String info() {
        return "detalles_info.html";
    }
}
