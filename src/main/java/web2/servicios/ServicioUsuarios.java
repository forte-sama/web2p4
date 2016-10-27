package web2.servicios;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.modelos.Usuario;
import web2.repositorios.RepositorioUsuario;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Long getCurrentTimeSetting() {

        if(repoUsuario.count() > 0) {
            Iterable<Usuario> usuarios = repoUsuario.findAll();

            for(Usuario u : usuarios) {
                return u.getMinutosPrevios();
            }
        }

        return 15L;
    }

    public boolean credencialesValidas(String email, String pass) {
        Iterable<Usuario> res = repoUsuario.findByCredentials(email,pass);
        List<Usuario> found = new ArrayList<>();
        CollectionUtils.addAll(found,res.iterator());

        return found.size() == 1;
    }
}
