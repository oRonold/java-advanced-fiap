package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.repository.RoleRepository;
import br.com.fiap.aula_mvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;




}
