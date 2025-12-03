/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;

import com.plataforma.domain.Rol;
import com.plataforma.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("roles", rolService.listar());
        return "roles/list"; // templates/roles/list.html
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/form";  // templates/roles/form.html
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Rol rol) {
        rolService.guardar(rol);
        return "redirect:/admin/roles";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("rol", rolService.buscarPorId(id));
        return "roles/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return "redirect:/admin/roles";
    }
}

