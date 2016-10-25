package web2.vistas;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import web2.servicios.ServicioCorreos;

import java.util.Locale;

@SpringUI(path = "/myui")
@Theme("valo")
public class MyUI extends UI {
    @Autowired
    private ServicioCorreos servicioMail;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        TextField tfield = new TextField("Hello IntelliJ IDEA");

        Button button = new Button("Presiona");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                servicioMail.enviarMensaje();
                Notification.show("Mensaje enviado?", Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        Calendar calendario = new Calendar();
        calendario.setLocale(Locale.US);
        calendario.setFirstVisibleDayOfWeek(2);
        calendario.setLastVisibleDayOfWeek(6);
        calendario.setFirstVisibleHourOfDay(8);
        calendario.setLastVisibleHourOfDay(17);

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(tfield);
        layout.addComponent(button);
        layout.addComponent(calendario);

        setContent(layout);
    }
}
