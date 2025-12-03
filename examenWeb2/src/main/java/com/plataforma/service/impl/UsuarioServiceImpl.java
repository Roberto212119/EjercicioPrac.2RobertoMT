/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.service.impl;

import com.plataforma.domain.Usuario;
import com.plataforma.repository.UsuarioRepository;
import com.plataforma.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
