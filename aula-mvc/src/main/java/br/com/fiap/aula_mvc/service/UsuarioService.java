package br.com.fiap.aula_mvc.service;

import br.com.fiap.aula_mvc.model.role.Role;
import br.com.fiap.aula_mvc.model.user.Usuario;
import br.com.fiap.aula_mvc.repository.RoleRepository;
import br.com.fiap.aula_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void saveUser(String username, String password, Set<String> roles) {
        Set<Role> roleSet = new HashSet<>();
        for(String roleName : roles){
            Role role = roleRepository.findByName(roleName);
            if(role != null) roleSet.add(role);
        }

        Usuario user = new Usuario(username, password, roleSet);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username);
        if(usuario == null) throw new UsernameNotFoundException("Usuário não encontrado");

        Set<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                authorities
        );
    }
}
