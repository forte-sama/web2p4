package web2.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.modelos.Usuario;
import web2.repositorios.RepositorioUsuario;

import javax.annotation.PostConstruct;

@Service
public class ServicioUsuarios {
    @Autowired
    private RepositorioUsuario repoUsuario;

    @PostConstruct
    public void init() {
        if(repoUsuario.count() < 1) {
            Usuario u = new Usuario();
            u.setEmail("mrcesar9402@gmail.com");
            u.setNombre("forte-sama");

            guardar(u);
        }
    }

    public void guardar(Usuario target) {
        if(repoUsuario.count() > 0) {
            repoUsuario.deleteAll();
            repoUsuario.save(target);
        }
        else {
            repoUsuario.save(target);
        }
    }
}
