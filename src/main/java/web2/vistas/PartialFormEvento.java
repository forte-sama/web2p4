package web2.vistas;

import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web2.modelos.Evento;
import web2.servicios.ServicioEventos;

@Component
@UIScope
public class PartialFormEvento extends FormLayout {

    @Autowired
    private ServicioEventos servicioEventos;

    private TextField titulo      = new TextField("Titulo");
    private TextField descripcion = new TextField("Desc");
    private DateField inicio      = new DateField("Inicio");
    private DateField fin         = new DateField("Fin");
    private Button btnGuardar     = new Button("E Guardar");
    private Button btnSalir       = new Button("E Salir");

    public PartialFormEvento() {
        setSizeUndefined();

        inicio.setResolution(Resolution.HOUR);
        fin.setResolution(Resolution.HOUR);

        titulo.setNullRepresentation("");
        titulo.setNullSettingAllowed(false);
        descripcion.setNullRepresentation("");
        descripcion.setNullSettingAllowed(false);

        HorizontalLayout buttons = new HorizontalLayout(btnGuardar, btnSalir);
        buttons.setSpacing(true);

        //Incluyendo los botones
        addComponents(titulo, descripcion, inicio, fin, buttons);

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
        //chequear si choca con algun evento del dia
        //chequear fin es luego de inicio
        if(true && true) {
            //crear objeto
            Evento e = new Evento();
            e.setCaption(titulo.getValue());
            e.setDescription(descripcion.getValue());
            e.setStart(inicio.getValue());
            e.setEnd(fin.getValue());

            //persistir objeto
            servicioEventos.guardar(e);
            //propagar cambio


            Notification.show("Evento Agregado", Notification.Type.HUMANIZED_MESSAGE);
        }
        else {
            Notification.show("Hubo un error", Notification.Type.WARNING_MESSAGE);
        }
    }

    private void salir() {
        titulo.setValue("");
        descripcion.setValue("");

        setVisible(false);
    }
}
