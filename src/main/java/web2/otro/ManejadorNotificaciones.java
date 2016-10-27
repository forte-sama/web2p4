package web2.otro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import web2.modelos.Evento;
import web2.servicios.ServicioCorreos;
import web2.servicios.ServicioEventos;
import web2.servicios.ServicioUsuarios;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

@Component
public class ManejadorNotificaciones {
    @Autowired
    private ServicioEventos servicioEventos;
    @Autowired
    private ServicioUsuarios servicioUsuarios;
    @Autowired
    private ServicioCorreos servicioCorreos;
    private HashMap<Long,Boolean> enviados;
    private Long minutosPrevios;

    public ManejadorNotificaciones() {
        enviados = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        minutosPrevios = servicioUsuarios.getCurrentTimeSetting();
    }

//    @Scheduled(cron = "0 0/15 * * * ?")
    @Scheduled(cron = "*/10 * * * * *") //cada 10 segs (para pruebas)
    public void notifyEvents() {
        //buscar en todos los eventos despues de ahora
        List<Evento> eventos = servicioEventos.getAllAfterNow();

        if(enviados == null) {
            enviados = new HashMap<>();
        }

        //obtener instante actual
        Instant now = Instant.now();

        //iterar en cada evento encontrado
        for(Evento e : eventos) {
            //determinar distancia entre ahora y tiempo de inicio de evento
            Duration gap = Duration.between(now,e.getStart().toInstant());
            //enviar correo solo si no ha sido enviado
            if(!enviados.containsKey(e.getId())) {
                //enviar correo solo si esta dentro de un rango de 30 minutos
                if(gap.toMinutes() <= (minutosPrevios != null ? minutosPrevios : 15)) {
                    enviados.put(e.getId(),true);
                    servicioCorreos.notificarParaEvento(e);
                }
            }
        }
    }
    @Scheduled(cron = "0 0/45 * * * ?") //cada 10 segs (para pruebas)
    public void updateTime() {
        minutosPrevios = servicioUsuarios.getCurrentTimeSetting();
    }
}
