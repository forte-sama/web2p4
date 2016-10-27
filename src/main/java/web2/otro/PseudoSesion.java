package web2.otro;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PseudoSesion {
    private boolean sesionIniciada;

    public void iniciarSesion() {
        sesionIniciada = true;
    }

    public boolean haIniciadoSesion() {
        return sesionIniciada;
    }
}
