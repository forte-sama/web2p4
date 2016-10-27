package web2.servicios;

import com.vaadin.ui.DateField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.modelos.Evento;
import web2.repositorios.RepositorioEvento;

import java.util.Date;
import java.util.List;

@Service
public class ServicioEventos {
    @Autowired
    private RepositorioEvento repoEventos;

    public void guardar(Evento e) {
        repoEventos.save(e);
    }

    public List<Evento> getAll() {
        return repoEventos.findAll();
    }

    public List<Evento> getAllAfterNow() {
        return repoEventos.findAllAfterToday(new Date());
    }

    public boolean sePuede(Date inicio, Date fin) {
        return repoEventos.getOverlappingEvents(inicio,fin).size() <= 0;
    }
}
