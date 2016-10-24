package web2.repositorios;

import org.springframework.data.repository.CrudRepository;
import web2.modelos.Usuario;

/**
 * Created by forte on 23/10/16.
 */
public interface RepositorioUsuario extends CrudRepository<Usuario,String> {
}
