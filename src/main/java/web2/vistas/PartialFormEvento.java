package web2.vistas;

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
public class PartialFormEvento extends FormLayout {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    private TextField email   = new TextField("Titulo");
    private TextField nombre  = new TextField("Desc");
    private Button btnGuardar = new Button("E Guardar");
    private Button btnSalir   = new Button("E Salir");

    public PartialFormEvento() {
        setSizeUndefined();

        email.addValidator(new EmailValidator("Debe ser email valido"));
        nombre.setNullRepresentation("");
        nombre.setNullSettingAllowed(false);

        HorizontalLayout buttons = new HorizontalLayout(btnGuardar, btnSalir);
        buttons.setSpacing(true);

        //Incluyendo los botones
        addComponents(email,nombre,buttons);

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
        ;
    }

    private void salir() {
        setVisible(false);
    }
}
