package br.com.fiap.aula_mvc.controller;

import br.com.fiap.aula_mvc.model.user.UserForm;
import br.com.fiap.aula_mvc.repository.RoleRepository;
import br.com.fiap.aula_mvc.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/register";
    }

    @PostMapping("register")
    @Transactional
    public String registerUser(@ModelAttribute("userForm") UserForm userForm){
        service.saveUser(userForm.getUsername(), encoder.encode(userForm.getPassword()), userForm.getRoles());
        return "redirect:/login";
    }

}
