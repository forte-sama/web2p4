package web2.repositorios;

import org.springframework.data.repository.CrudRepository;
import web2.modelos.Evento;

public interface RepositorioEvento extends CrudRepository<Evento,Long> {

}
