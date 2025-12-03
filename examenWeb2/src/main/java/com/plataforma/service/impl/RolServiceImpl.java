/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.service.impl;

import com.plataforma.domain.Rol;
import com.plataforma.repository.RolRepository;
import com.plataforma.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repo;

    public RolServiceImpl(RolRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Rol> listar() {
        return repo.findAll();
    }

    @Override
    public Rol buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Rol guardar(Rol rol) {
        return repo.save(rol);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
