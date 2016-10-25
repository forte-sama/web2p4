package web2.modelos;

import com.vaadin.shared.ui.calendar.CalendarState;
import com.vaadin.ui.Component;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import static com.vaadin.shared.ui.calendar.CalendarState.*;

@Entity
public class Evento implements CalendarEvent, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String caption;
    private String description;
    private Date start;
    private Date end;

    public Evento() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String titulo) {
        this.caption = titulo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descripcion) {
        this.description = descripcion;
    }
    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }
    public String getStyleName() {
        return null;
    }
    public boolean isAllDay() {
        return false;
    }
}
