package web2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import web2.modelos.Evento;

public interface RepositorioEvento extends JpaRepository<Evento,Long> {
}
