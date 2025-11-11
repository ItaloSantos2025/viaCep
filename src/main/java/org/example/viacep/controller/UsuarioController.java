package org.example.viacep.controller;

import org.example.viacep.dto.UsuarioRequestDTO;
import org.example.viacep.entity.Usuario;
import org.example.viacep.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioService.cadastrarUsuario(requestDTO);
        return ResponseEntity.created(URI.create("/usuarios/" + usuario.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}