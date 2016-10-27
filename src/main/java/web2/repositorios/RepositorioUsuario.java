package web2.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web2.modelos.Usuario;

/**
 * Created by forte on 23/10/16.
 */
public interface RepositorioUsuario extends CrudRepository<Usuario,String> {
    @Query("select u from Usuario u where u.email=:email and u.password=:pass")
    Iterable<Usuario> findByCredentials(@Param("email") String email, @Param("pass") String pass);
}
