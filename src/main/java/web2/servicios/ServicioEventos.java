package web2.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.modelos.Evento;
import web2.repositorios.RepositorioEvento;

@Service
public class ServicioEventos {
    @Autowired
    private RepositorioEvento repoEventos;

    public void guardar(Evento e) {
        repoEventos.save(e);
    }

    public Iterable<Evento> getAll() {
        return repoEventos.findAll();
    }
}
