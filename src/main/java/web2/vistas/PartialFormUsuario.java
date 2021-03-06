package web2.vistas;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web2.modelos.Usuario;
import web2.servicios.ServicioUsuarios;

@Component
@UIScope
public class PartialFormUsuario extends FormLayout {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    private TextField email     = new TextField("Email");
    private TextField nombre    = new TextField("Nombre");
    private PasswordField pass  = new PasswordField("Password");
    private Button btnGuardar   = new Button("Guardar");
    private Button btnSalir     = new Button("Salir");
    private ComboBox selectTime = new ComboBox("Tiempo previo (Minutos)");

    public PartialFormUsuario() {
        setSizeUndefined();

        email.addValidator(new EmailValidator("Debe ser email valido"));
        nombre.setNullRepresentation("");
        nombre.setNullSettingAllowed(false);
        pass.setNullRepresentation("");
        pass.setNullSettingAllowed(false);

        selectTime.addItem("15");
        selectTime.addItem("30");
        selectTime.addItem("45");
        selectTime.addItem("60");
        selectTime.addItem("90");
        selectTime.setValue("15");

        HorizontalLayout buttons = new HorizontalLayout(btnGuardar, btnSalir);
        buttons.setSpacing(true);

        //Incluyendo los botones
        addComponents(nombre,email,pass,selectTime,buttons);

        btnGuardar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnGuardar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                guardar();
            }
        });
        btnSalir.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                salir();
            }
        });
    }

    private void guardar() {
        if(email.isValid()) {
            Usuario target = new Usuario();
            target.setEmail(email.getValue());
            target.setNombre(nombre.getValue());
            target.setPassword(pass.getValue());
            target.setMinutosPrevios(Long.parseLong((String)selectTime.getValue()));

            servicioUsuarios.guardar(target);

            email.setValue("");
            nombre.setValue("");
            pass.setValue("");

            Notification.show("Cambios Guardados", Notification.Type.HUMANIZED_MESSAGE);
        }
        else {
            Notification.show("Email debe ser valido", Notification.Type.WARNING_MESSAGE);
        }
    }

    private void salir() {
        email.setValue("");
        nombre.setValue("");
        pass.setValue("");

        setVisible(false);
    }
}
