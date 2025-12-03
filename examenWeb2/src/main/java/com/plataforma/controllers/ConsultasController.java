/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;

import com.plataforma.domain.Usuario;
import com.plataforma.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {

    private final UsuarioService usuarioService;

    public ConsultasController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String index() {
        return "consultas/index"; // templates/consultas/index.html
    }

    @GetMapping("/rol")
    public String porRol(@RequestParam String rol, Model model) {
        List<Usuario> res = usuarioService.listar().stream()
                .filter(u -> u.getRol() != null &&
                        u.getRol().getNombre() != null &&
                        u.getRol().getNombre().equalsIgnoreCase(rol))
                .collect(Collectors.toList());
        model.addAttribute("usuarios", res);
        return "consultas/lista"; // templates/consultas/lista.html
    }

    @GetMapping("/rango")
    public String porRango(@RequestParam String desde, @RequestParam String hasta, Model model) {
        LocalDateTime d1 = LocalDateTime.parse(desde);
        LocalDateTime d2 = LocalDateTime.parse(hasta);
        List<Usuario> res = usuarioService.listar().stream()
                .filter(u -> u.getFechaCreacion() != null &&
                             !u.getFechaCreacion().isBefore(d1) &&
                             !u.getFechaCreacion().isAfter(d2))
                .collect(Collectors.toList());
        model.addAttribute("usuarios", res);
        return "consultas/lista";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String q, Model model) {
        String ql = q.toLowerCase();
        List<Usuario> res = usuarioService.listar().stream()
                .filter(u -> (u.getEmail() != null && u.getEmail().toLowerCase().contains(ql)) ||
                             (u.getNombre() != null && u.getNombre().toLowerCase().contains(ql)))
                .collect(Collectors.toList());
        model.addAttribute("usuarios", res);
        return "consultas/lista";
    }
}
