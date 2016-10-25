package web2.vistas;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;
import org.hibernate.event.spi.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import web2.modelos.Evento;
import web2.otro.MyEventProvider;
import web2.servicios.ServicioEventos;
import web2.servicios.ServicioUsuarios;

import java.util.*;

@SpringUI(path = "/")
@Theme("valo")
public class Agenda extends UI {
    @Autowired
    private ServicioUsuarios servicioUsuarios;
    @Autowired
    private ServicioEventos servicioEventos;
    @Autowired
    private PartialFormUsuario formUsuario;
    @Autowired
    private PartialFormEvento formEvento;

    private Calendar agenda;

    @Override
    protected void init(VaadinRequest request) {
        //creando contenedores
        HorizontalLayout top    = new HorizontalLayout();
        HorizontalLayout middle = new HorizontalLayout();
        VerticalLayout main     = new VerticalLayout();

        //creando componentes
        agenda = new Calendar();
        agenda.setLocale(Locale.US);
        agenda.setFirstVisibleDayOfWeek(2);
        agenda.setLastVisibleDayOfWeek(6);
        agenda.setWidth("600px");
        agenda.setHeight("100%");
        agenda.setEventProvider(new MyEventProvider(servicioEventos));

        //pruebas con eventos
//        GregorianCalendar start = new GregorianCalendar();
//        GregorianCalendar end   = new GregorianCalendar();
//        end.add(java.util.Calendar.HOUR, 2);
//        agenda.addEvent(new BasicEvent("Calendar study",
//                "Learning how to use Vaadin Calendar",
//                start.getTime(), end.getTime()));

        Button btnCredenciales = new Button("Manejo Credenciales", FontAwesome.LOCK);
        btnCredenciales.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                formUsuario.setVisible(true);
                //esconder form eventos
                formEvento.setVisible(false);
            }
        });

        Button btnEventos = new Button("Agregar Evento", FontAwesome.CALENDAR);
        btnEventos.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                formEvento.setVisible(true);
                //esconder form usuarios
                formUsuario.setVisible(false);
            }
        });

        top.setSpacing(true);
        top.addComponent(btnCredenciales);
        top.addComponent(btnEventos);

        middle.setSizeFull();
        middle.setSpacing(true);
        middle.addComponent(agenda);
        middle.addComponent(formEvento);
        middle.addComponent(formUsuario);

        main.setMargin(true);
        main.addComponent(top);
        main.addComponent(middle);

        setContent(main);

        formUsuario.setVisible(false);
        formEvento.setVisible(false);
    }
}

