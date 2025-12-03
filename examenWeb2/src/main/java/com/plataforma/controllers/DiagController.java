/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.controllers;


import com.plataforma.domain.Usuario;
import com.plataforma.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/_diag")
public class DiagController {

    private final UsuarioRepository repo;

    public DiagController(UsuarioRepository repo) { this.repo = repo; }

    // ¿La app ve la BD?
    @GetMapping("/db")
    public Map<String,Object> db() {
        long total = repo.count();
        boolean tieneCarlos = repo.findByEmail("c.ramirez@correo.com").isPresent();
        return Map.of("usuarios_totales", total, "existe_carlos", tieneCarlos);
    }

    // ¿Quién soy tras login?
    @GetMapping("/whoami")
    public Map<String,Object> whoami(Authentication auth) {
        return Map.of("usuario", auth.getName(), "roles", auth.getAuthorities());
    }

    // Buscar por email (para probar el repo)
    @GetMapping("/find")
    public Object find(@RequestParam String email) {
        return repo.findByEmail(email).map(Usuario::getEmail).orElse("NO_ENCONTRADO");
    }
}

