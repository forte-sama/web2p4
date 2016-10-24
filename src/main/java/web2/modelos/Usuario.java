package web2.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    String email;
    String nombreCompleto;

    //constructors
    public Usuario() { }

    //custom methods


    //getters & setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNombre() {
        return nombreCompleto;
    }
    public void setNombre(String nombre) {
        this.nombreCompleto = nombre;
    }
}
