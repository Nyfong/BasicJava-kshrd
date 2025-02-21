package synchronizedtest;
class Message {
    private String msg;
    private boolean hasMessage = false;

    public synchronized void sendMessage(String message) {
        while (hasMessage) {
            try {
                wait(); // Wait if a message is already there
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.msg = message;
        hasMessage = true;
        System.out.println("Sent: " + message);
        notify(); // Notify the receiver that message is ready
    }

    public synchronized void receiveMessage() {
        while (!hasMessage) {
            try {
                wait(); // Wait until there is a message
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Received: " + msg);
        hasMessage = false;
        notify(); // Notify sender that message is read
    }
}

class Sender extends Thread {
    private Message message;

    public Sender(Message message) {
        this.message = message;
    }

    public void run() {
        message.sendMessage("Hello, Receiver!");
    }
}

class Receiver extends Thread {
    private Message message;

    public Receiver(Message message) {
        this.message = message;
    }

    public void run() {
        message.receiveMessage();
    }
}

public class CommonlyUse {
    public static void main(String[] args) {
        Message msg = new Message();

        Sender sender = new Sender(msg);
        Receiver receiver = new Receiver(msg);

        receiver.start(); // Start receiver first
        sender.start();   // Start sender after
    }
}
