package web2.vistas;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventResizeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import web2.modelos.Evento;
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
        agenda.setFirstVisibleHourOfDay(7);
        agenda.setFirstVisibleHourOfDay(8);
        agenda.setLastVisibleHourOfDay(17);
        agenda.setWidth("600px");
        agenda.setHeight("100%");
        BeanItemContainer<Evento> contenedor = new BeanItemContainer<>(Evento.class,servicioEventos.getAll());
        agenda.setContainerDataSource(contenedor);

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

        Button btnGuardar = new Button("Guardar Horario", FontAwesome.SAVE);
        btnGuardar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                for(Evento e : contenedor.getItemIds()) {
                    servicioEventos.guardar(e);
                }

                Notification.show("Horario ha sido guardado", Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        top.setSpacing(true);
        top.addComponents(btnCredenciales,btnEventos,btnGuardar);

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

