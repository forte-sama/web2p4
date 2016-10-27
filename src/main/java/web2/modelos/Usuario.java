package web2.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
    @Id
    private String email;
    private String password;
    private String nombreCompleto;

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
