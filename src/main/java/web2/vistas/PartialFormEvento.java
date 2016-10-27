package web2.vistas;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import web2.modelos.Evento;
import web2.servicios.ServicioEventos;

import java.util.Date;

@Component
@UIScope
public class PartialFormEvento extends FormLayout {

    @Autowired
    private ServicioEventos servicioEventos;

    private TextField titulo      = new TextField("Titulo");
    private TextField descripcion = new TextField("Desc");
    private DateField inicio      = new DateField("Inicio");
    private DateField fin         = new DateField("Fin");
    private Button btnGuardar     = new Button("Agregar Evento");
    private Button btnSalir       = new Button("Salir");
    private BeanItemContainer<Evento> eventFeed;

    public PartialFormEvento() {
        setSizeUndefined();

        inicio.setResolution(Resolution.MINUTE);
        fin.setResolution(Resolution.MINUTE);

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
        Date i = inicio.getValue();
        Date f = fin.getValue();

        boolean noSonNulos = i != null && f != null;

        if(noSonNulos && i.compareTo(f) < 0) {
            //crear objeto
            Evento e = new Evento();
            e.setCaption(titulo.getValue());
            e.setDescription(descripcion.getValue());
            e.setStart(inicio.getValue());
            e.setEnd(fin.getValue());

            //persistir objeto
            servicioEventos.guardar(e);
            //propagar cambio
            eventFeed.addBean(e);
            //notificar
            Notification.show("Evento Agregado", Notification.Type.HUMANIZED_MESSAGE);

            //limpiar campos
            titulo.setValue("");
            descripcion.setValue("");
            inicio.setValue(null);
            fin.setValue(null);
        }
        else {
            Notification.show("Hubo error al agregar evento", Notification.Type.WARNING_MESSAGE);
        }
    }

    private void salir() {
        titulo.setValue("");
        descripcion.setValue("");

        setVisible(false);
    }

    public void setEventFeed(BeanItemContainer<Evento> eventoFeed) {
        this.eventFeed = eventoFeed;
    }
}
