package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Usuario;
import com.example.winstonShaw.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByUserName(username);
        if(usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }
        else {
            throw new UsernameNotFoundException("El Username ingresado no existe");
        }
    }
}
