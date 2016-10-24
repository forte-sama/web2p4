package web2.vistas;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import web2.servicios.ServicioUsuarios;

import java.util.Locale;

@SpringUI(path = "/")
@Theme("valo")
public class Agenda extends UI {
    @Autowired
    private ServicioUsuarios servicioUsuarios;
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
        agenda.setFirstVisibleHourOfDay(8);
        agenda.setLastVisibleHourOfDay(17);
        agenda.setWidth("600px");
        agenda.setHeight("100%");

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
