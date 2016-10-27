package web2.vistas;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web2.otro.PseudoSesion;
import web2.servicios.ServicioUsuarios;

import javax.swing.*;

@Component
@UIScope
public class LoginUI extends VerticalLayout {
    @Autowired
    private ServicioUsuarios servicioUsuarios;
    @Autowired
    private PseudoSesion sesion;

    private TextField email        = new TextField("Email");
    private PasswordField password = new PasswordField("Password");
    private Button btnLogin        = new Button("Iniciar Sesion");

    public LoginUI() {
        GridLayout grid = new GridLayout(5,5);
        VerticalLayout layout = new VerticalLayout();


        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(servicioUsuarios.credencialesValidas(email.getValue(),password.getValue())) {
                    sesion.iniciarSesion();
                    getUI().getPage().setLocation("http://localhost:8080");
                }
                else {
                    Notification.show("username/password invalidos", Notification.Type.WARNING_MESSAGE);
                }
            }
        });

        layout.addComponents(email,password,btnLogin);
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setSizeFull();

        grid.addComponent(layout,2,1);
        grid.setMargin(true);
        grid.setSpacing(true);
        grid.setSizeFull();

        addComponent(grid);
    }
}
