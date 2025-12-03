/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/post-login")
    public String postLogin(Authentication auth) {
        String role = auth.getAuthorities().iterator().next().getAuthority(); // ROLE_XXX
        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/admin/usuarios";
        } else if ("ROLE_PROFESOR".equals(role)) {
            return "redirect:/prof/reportes";
        } else {
            return "redirect:/perfil";
        }
    }
}
