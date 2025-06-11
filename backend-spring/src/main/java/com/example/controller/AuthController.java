package com.example.controller;

import com.example.model.Usuario;
import com.example.model.Usuario.Rol;
import com.example.repository.UsuarioRepository;
import com.example.repository.PadreRepository;
import com.example.repository.CitaRepository;
import com.example.repository.NinoRepository;
import com.example.model.Padre;
import com.example.model.Cita;
import com.example.model.Nino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PadreRepository padreRepository;


    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private NinoRepository ninoRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
        
    }

    @PostMapping("/login")
    public String login(@RequestParam String codigo,
                        @RequestParam String contraseña,
                        HttpSession session,
                        Model model) {

        Optional<Usuario> usuarioDB = usuarioRepository.findByCodigoAndContraseña(codigo, contraseña);

        if (usuarioDB.isPresent()) {
            Usuario u = usuarioDB.get();

            session.setAttribute("usuario", u.getNombre());
            session.setAttribute("rol", u.getRol());
            session.setAttribute("codigo", u.getCodigo());
            session.setAttribute("usuarioObj", u); // Objeto completo

            System.out.println("ROL OBTENIDO: " + u.getRol());
            System.out.println("TIPO DE ROL: " + u.getRol().getClass().getName());

            switch (u.getRol()) {
                case padre:
                    return "redirect:/padre";
                case terapeuta:
                    return "redirect:/terapeuta";
                case admin:
                    return "redirect:/administrador";
                default:
                    model.addAttribute("error", "Rol desconocido");
                    return "login";
            }

        } else {
            model.addAttribute("error", "Código o contraseña incorrectos");
            return "login";
        }
    }


    @GetMapping("/padre")
    public String mostrarBienvenida(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("rol", session.getAttribute("rol"));

        if ("padre".equals(session.getAttribute("rol"))) {
            System.out.println("Entró como padre"); // ← 1

            Usuario u = (Usuario) session.getAttribute("usuarioObj");
            if (u == null) {
                System.out.println("usuarioObj en sesión es NULL"); // ← 2
            } else {
                System.out.println("usuarioObj: " + u.getId_usuario()); // ← 3
                Padre padre = padreRepository.findById(u.getId_usuario()).orElse(null);

                if (padre != null) {
                    System.out.println("Padre encontrado: " + padre.getId_padre()); // ← 4
                    List<Nino> ninos = ninoRepository.findByIdPadre(padre.getId_padre());
                    System.out.println("Niños encontrados: " + ninos.size()); // ← 5

                    List<Cita> citas = new ArrayList<>();
                    for (Nino nino : ninos) {
                        citas.addAll(citaRepository.findByIdNino(nino.getId_nino()));
                    }
                    System.out.println("Citas encontradas: " + citas.size()); // ← 6
                    model.addAttribute("citas", citas);
                } else {
                    System.out.println("No se encontró el padre"); // ← 7
                }
            }
        }
        System.out.println("usuarioObj en sesión: " + session.getAttribute("usuarioObj"));
        return "padre";
    }


    @GetMapping("/ejemplo1")
    public String mostrarEjemplo1(Model model) {
        model.addAttribute("queso", "Contenido de ejemplo");
        return "ejemplo1";
    }

    @GetMapping("/psicologos")
    public String mostrarPsicologos(Model model) {
        return "psicologos";
    }

        @GetMapping("/agenda")
    public String mostrarAgenda(Model model) {
        return "agenda";
    }
    
    @GetMapping("/recompensas")
    public String mostrarRecompensas(Model model) {
        return "recompensas";
    }

    
    @GetMapping("/terapeuta")
    public String mostrarVistaTerapeuta(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioObj");

        if (usuario != null && usuario.getRol() == Rol.terapeuta) {
            model.addAttribute("terapeuta", usuario); // Pasamos el objeto terapeuta
            return "terapeuta";
        }

        return "redirect:/login"; // redirigir si no es terapeuta
    }
    

    @GetMapping("/administrador")
    public String mostrarVistaAdministrador(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioObj");

        if (usuario != null && usuario.getRol() == Rol.admin) {
            model.addAttribute("admin", usuario); // <- Esta línea es la clave
            return "administrador";
        }

        return "redirect:/login"; // redirigir si no es admin
    }
    
}
