package oop.interfacetest;

import javax.swing.text.StyleContext;
import java.lang.annotation.Inherited;

// Notification Interface (separate file - Notification.java)
interface Notification {
    void send(String message, String recipient);
}

// EmailNotification Class (separate file - EmailNotification.java)
class EmailNotification implements Notification {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending email to " + recipient + ": " + message);
        // Logic to send email (e.g., using an email library)
    }


}

// SMSNotification Class (separate file - SMSNotification.java)
class SMSNotification implements Notification {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
        // Logic to send SMS (e.g., using an SMS gateway)
    }
}

abstract class test implements Notification {

}

// NotificationSender Class (separate file - NotificationSender.java)
public class NotificationSender {

    public void sendNotification(Notification notification , String message, String recipient) {
        notification.send(message, recipient);
    }

    public static void main(String[] args) {
        NotificationSender sender = new NotificationSender();

        Notification emailNotification = new EmailNotification();
        sender.sendNotification(emailNotification, "Hello, how are you?", "user@example.com");

        Notification smsNotification = new SMSNotification();
        sender.sendNotification(smsNotification, "Meeting reminder!", "+1234567");

    }
}