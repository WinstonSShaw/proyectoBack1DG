package com.example.winstonShaw.security;

import com.example.winstonShaw.entities.Usuario;
import com.example.winstonShaw.entities.UsuarioRol;
import com.example.winstonShaw.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode("usuario");
        String hash2 = passwordEncoder.encode("adm");
        Usuario usuario1 = new Usuario("Usuario", "usuario", "usuario@usuario.com", hash, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuario1);

        Usuario usuario2 = new Usuario("Admin", "adm", "adm@adm.com", hash2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuario2);
    }
}
