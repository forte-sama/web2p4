package web2.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/holamundo")
@Theme("valo")
public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        TextField tfield = new TextField("Hello IntelliJ IDEA");

        Button button = new Button("Presiona");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Prueba", Notification.Type.HUMANIZED_MESSAGE);
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
