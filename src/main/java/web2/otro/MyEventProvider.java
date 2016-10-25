package web2.otro;

import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;
import org.apache.commons.collections.CollectionUtils;
import web2.modelos.Evento;
import web2.servicios.ServicioEventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyEventProvider implements CalendarEventProvider {
    private ServicioEventos source;

    public MyEventProvider(ServicioEventos servicio) {
        this.source = servicio;
    }

    @Override
    public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
        List<CalendarEvent> results = new ArrayList<>();

        Iterable<Evento> iterable_res = source.getAll();
        CollectionUtils.addAll(results,iterable_res.iterator());

        return results;
    }
}
