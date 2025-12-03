/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.service;

import com.plataforma.domain.Rol;
import java.util.List;

public interface RolService {
    List<Rol> listar();
    Rol buscarPorId(Long id);
    Rol guardar(Rol rol);
    void eliminar(Long id);
}
