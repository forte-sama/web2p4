package web2.servicios;

import com.sendgrid.*;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class ServicioCorreos {
    public void enviarMensaje() {
        Email from = new Email("estudiante_de_camacho@clasewebavanzada.jodio");
        String subject = "Prueba";
        Email to = new Email("mrcesar9402@gmail.com");
        Content content = new Content("text/plain", "Prueba con sendgrid");
        Mail mail = new Mail(from, subject, to, content);

        String k = "SG.o78pU9wOT12"+"7V0Vkv5Ixxg.q-KsqFh"+"fQMkUplz_eeYvq_cO"+"SEZuMxixhrmKdwDVq3Q";
        SendGrid sg = new SendGrid(k);
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Hubo un errorsito tratando de enviar correo");
        }
    }
}
