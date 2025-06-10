package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasPublicasController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/actividades")
    public String actividades() {
        return "actividades";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/guiapadres")
    public String guiaPadres() {
        return "guiapadres";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

    @GetMapping("/opiniones")
    public String opiniones() {
        return "opiniones";
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "servicios";
    }

    @GetMapping("/")
    public String redirigirInicio() {
        return "index";
    }
}
