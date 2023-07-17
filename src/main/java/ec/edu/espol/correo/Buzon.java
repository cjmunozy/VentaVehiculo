/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.correo;

import ec.edu.espol.classes.Comprador;
import ec.edu.espol.classes.Oferta;
import ec.edu.espol.classes.Vehiculo;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author crisj
 */
public class Buzon {
    private final String smtpsHost = "smtp.gmail.com";
    private Properties props = System.getProperties();
    private Session session;
    private final String correo = "ventavehiculo2023@gmail.com";
    private final String contraseña = "zwhemwtincvklsyc";

    public Buzon() {
        props.put("mail.smtp.host", this.smtpsHost);
        props.put("mail.smtp.user", this.correo);
        props.put("mail.smtp.clave", this.contraseña);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.port", "587");
        session = Session.getDefaultInstance(props);
    }
    
    public void enviarCorreo(Oferta o){
        Comprador c = o.getComprador();
        Vehiculo v = o.getVehiculo();
        Message message = new MimeMessage(this.session);
        try {
            message.setFrom(new InternetAddress(this.correo));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getCorreo()));
            message.setSubject("Oferta Aceptada");
            message.setText("¡Felicidades!\nSu oferta de $" + o.getPrecio() + " por el vehículo " + v.getMarca() + " " + v.getModelo() + " fue aceptada.\nPuede acercarse a la agencia para finalizar el proceso de adquisición.");
            Transport transport = this.session.getTransport("smtps");
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
