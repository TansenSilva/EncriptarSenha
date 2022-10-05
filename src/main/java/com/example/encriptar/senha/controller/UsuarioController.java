package com.example.encriptar.senha.controller;

import com.example.encriptar.senha.model.UsuarioModel;
import com.example.encriptar.senha.repository.UsuarioRepository;
import com.example.encriptar.senha.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping(path = "/listarPorId/{id}")
    public ResponseEntity<Optional<UsuarioModel>> listarUsuarioPorId(Integer id){
        return ResponseEntity.ok(service.listarPorId(id));
    }

    @PostMapping(path = "/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario){
        return ResponseEntity.ok(service.cadastrar(usuario));
    }

    @GetMapping(path = "/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password){
        Optional<UsuarioModel> optUsuario = service.listarPorLogin(login);
        if(optUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password,usuario.getSenha());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
}
