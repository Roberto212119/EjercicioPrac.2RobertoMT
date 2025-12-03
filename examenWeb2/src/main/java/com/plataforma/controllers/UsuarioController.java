/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;

import com.plataforma.domain.Rol;
import com.plataforma.domain.Usuario;
import com.plataforma.service.RolService;
import com.plataforma.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "admin/usuarios"; // templates/admin/usuarios.html
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listar());
        return "usuarios/form"; // si prefieres, crea templates/usuarios/form.html
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, @RequestParam Long rolId) {
        Rol rol = rolService.buscarPorId(rolId);
        usuario.setRol(rol);
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario u = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", u);
        model.addAttribute("roles", rolService.listar());
        return "usuarios/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "usuarios/detalle"; // templates/usuarios/detalle.html
    }
}
