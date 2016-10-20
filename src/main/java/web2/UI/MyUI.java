package web2.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import web2.servicios.MailService;

@SpringUI(path = "/")
@Theme("valo")
public class MyUI extends UI {
    @Autowired
    private MailService servicioMail;

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

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(tfield);
        layout.addComponent(button);

        setContent(layout);
    }
}
