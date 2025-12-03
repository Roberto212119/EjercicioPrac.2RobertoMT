/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;

import com.plataforma.domain.Usuario;
import com.plataforma.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/prof")
public class ProfesorController {

    private final UsuarioService usuarioService;

    public ProfesorController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/reportes")
    public String reportes(Model model) {
        List<Usuario> todos = usuarioService.listar();
        List<Usuario> noAdmins = todos.stream()
                .filter(u -> u.getRol() != null && !"ADMIN".equalsIgnoreCase(u.getRol().getNombre()))
                .collect(Collectors.toList());
        long activos = todos.stream().filter(u -> Boolean.TRUE.equals(u.getActivo())).count();
        long inactivos = todos.size() - activos;

        model.addAttribute("noAdmins", noAdmins);
        model.addAttribute("activos", activos);
        model.addAttribute("inactivos", inactivos);
        return "prof/reportes"; // templates/prof/reportes.html
    }
}
