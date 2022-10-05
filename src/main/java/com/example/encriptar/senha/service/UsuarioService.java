package com.example.encriptar.senha.service;

import com.example.encriptar.senha.model.UsuarioModel;
import com.example.encriptar.senha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioModel> listarTodos(){
        return repository.findAll();
    }
    public Optional<UsuarioModel> listarPorId(Integer id){
        return repository.findById(id);
    }
    public Optional<UsuarioModel> listarPorLogin(String login){
        return repository.findByLogin(login);
    }
    public UsuarioModel cadastrar(UsuarioModel usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
}
