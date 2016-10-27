package web2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web2.modelos.Evento;

import java.util.Date;
import java.util.List;

public interface RepositorioEvento extends JpaRepository<Evento,Long> {
    @Query("select e from Evento e where e.start > :hoy")
    List<Evento> findAllAfterToday(@Param("hoy") Date date);

    @Query("select e from Evento e where :i >= e.start and :f <= e.end")
//    date(e.start)=date(:i) and date(:f)=date(e.end) " +
//            "and (:i <= e.start and :f > e.start) " +
//            "or  (:i < e.end    and :f >= e.end) " +
    List<Evento> getOverlappingEvents(@Param("i") Date inicio, @Param("f") Date fin);
}
