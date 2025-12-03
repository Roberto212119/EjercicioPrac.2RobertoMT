/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.plataforma.service;

import com.plataforma.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario buscarPorId(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

    Usuario buscarPorEmail(String email);
}
